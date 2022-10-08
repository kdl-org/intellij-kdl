// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import dev.kdl.lang.psi.ext.KdlCommentableItem;

public interface KdlPsiNodeBlock extends KdlCommentableItem {

  @NotNull
  List<KdlPsiEscline> getEsclineList();

  @NotNull
  KdlPsiIdentifier getIdentifier();

  @Nullable
  KdlPsiNodeChildren getNodeChildren();

  @NotNull
  List<KdlPsiNodePropOrArg> getNodePropOrArgList();

  @Nullable
  KdlPsiType getType();

  @Nullable
  PsiElement getSlashdash();

}
