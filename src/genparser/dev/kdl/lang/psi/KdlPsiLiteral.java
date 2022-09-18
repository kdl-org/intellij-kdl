// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import dev.kdl.lang.psi.ext.KdlElement;

public interface KdlPsiLiteral extends KdlElement {

  @Nullable
  KdlPsiBoolean getBoolean();

  @Nullable
  KdlPsiNull getNull();

  @Nullable
  KdlPsiNumber getNumber();

  @Nullable
  KdlPsiString getString();

}
