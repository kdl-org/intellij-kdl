// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import dev.kdl.lang.psi.ext.KdlCommentableItem;

public interface KdlPsiNodeChildren extends KdlCommentableItem {

  @NotNull
  List<KdlPsiEscline> getEsclineList();

  @NotNull
  List<KdlPsiNodeBlock> getNodeBlockList();

  @NotNull
  PsiElement getLBrace();

  @Nullable
  PsiElement getRBrace();

  @Nullable
  PsiElement getSlashdash();

}
