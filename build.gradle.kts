import org.ajoberstar.grgit.Grgit
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask


fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("org.jetbrains.intellij") version "1.9.0"
    id("org.jetbrains.changelog") version "1.3.1"
    id("org.jetbrains.grammarkit") version "2022.3.1"
    id("org.ajoberstar.grgit") version "5.0.0"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

changelog {
    version.set(properties("pluginVersion"))
    groups.set(emptyList())
}

tasks {
    wrapper {
        gradleVersion = properties("gradleVersion")
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))
        untilBuild.set(properties("pluginUntilBuild"))

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription.set(
            projectDir.resolve("README.md").readText().lines().run {
                val start = "<!-- Plugin description -->"
                val end = "<!-- Plugin description end -->"

                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end))
            }.joinToString("\n").run { markdownToHTML(this) }
        )

        // Get the latest available change notes from the changelog file
        changeNotes.set(provider {
            changelog.run {
                getOrNull(properties("pluginVersion")) ?: getLatest()
            }.toHTML()
        })
    }

    // Configure UI tests plugin
    // Read more: https://github.com/JetBrains/intellij-ui-test-robot
    runIdeForUiTests {
        systemProperty("robot-server.port", "8082")
        systemProperty("ide.mac.message.dialogs.as.sheets", "false")
        systemProperty("jb.privacy.policy.text", "<!--999.999-->")
        systemProperty("jb.consents.confirmation.enabled", "false")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        // pluginVersion is based on the SemVer (https://semver.org) and supports pre-release labels, like 2.1.7-alpha.3
        // Specify pre-release label to publish the plugin in a custom Release Channel automatically. Read more:
        // https://plugins.jetbrains.com/docs/intellij/deployment.html#specifying-a-release-channel
        channels.set(listOf(properties("pluginVersion").split('-').getOrElse(1) { "default" }.split('.').first()))
    }
}

// lexer and parser

sourceSets {
    main {
        java.srcDirs("src/genparser")
    }
}

val generateKdlLexer = task<GenerateLexerTask>("generateKdlLexer") {
    sourceFile.set(file("src/main/grammars/KdlLexer.flex"))
    targetDir.set("src/genparser/dev/kdl/lang/lexer")
    targetClass.set("KdlLexer")
    purgeOldFiles.set(true)
}

val generateKdlStringLexer = task<GenerateLexerTask>("generateKdlStringLexer") {
    sourceFile.set(file("src/main/grammars/KdlStringLexer.flex"))
    targetDir.set("src/genparser/dev/kdl/lang/escape")
    targetClass.set("KdlStringLexer")
    purgeOldFiles.set(true)
}

val generateKdlParser = task<GenerateParserTask>("generateKdlParser") {
    sourceFile.set(file("src/main/grammars/KdlParser.bnf"))
    targetRoot.set("src/genparser")
    pathToParser.set("dev/kdl/lang/parser/KdlParser.java")
    pathToPsiRoot.set("dev/kdl/lang/psi")
    purgeOldFiles.set(true)

    dependsOn(generateKdlLexer, generateKdlStringLexer)
}


tasks {
    val updateTestSuite by registering {
        doLast {
            val repoPath = buildDir.resolve("testSuite")
            val testsPath = projectDir.resolve("src/test/testData/testSuite")
            mkdir(repoPath)
            Grgit.clone {
                dir = repoPath
                uri = "https://github.com/kdl-org/kdl.git"
            }
            copy {
                from(repoPath.resolve("tests/test_cases"))
                into(testsPath)
            }
        }
    }
}