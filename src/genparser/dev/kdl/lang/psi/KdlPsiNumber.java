// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import dev.kdl.lang.psi.ext.KdlElement;

public interface KdlPsiNumber extends KdlElement {

  @Nullable
  PsiElement getBinaryLiteral();

  @Nullable
  PsiElement getDecimalLiteral();

  @Nullable
  PsiElement getHexLiteral();

  @Nullable
  PsiElement getOctalLiteral();

}
