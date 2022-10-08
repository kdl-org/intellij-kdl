// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import dev.kdl.lang.psi.ext.KdlCommentableItem;

public interface KdlPsiNodePropOrArg extends KdlCommentableItem {

  @Nullable
  KdlPsiArg getArg();

  @NotNull
  List<KdlPsiEscline> getEsclineList();

  @Nullable
  KdlPsiProp getProp();

  @Nullable
  PsiElement getSlashdash();

}
