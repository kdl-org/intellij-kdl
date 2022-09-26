// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static dev.kdl.lang.psi.ext.KdlElementTypes.*;
import dev.kdl.lang.psi.ext.KdlElementImpl;
import dev.kdl.lang.psi.*;

public class KdlPsiNodeBlockImpl extends KdlElementImpl implements KdlPsiNodeBlock {

  public KdlPsiNodeBlockImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull KdlPsiVisitor visitor) {
    visitor.visitNodeBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof KdlPsiVisitor) accept((KdlPsiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<KdlPsiEscline> getEsclineList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, KdlPsiEscline.class);
  }

  @Override
  @NotNull
  public KdlPsiIdentifier getIdentifier() {
    return findNotNullChildByClass(KdlPsiIdentifier.class);
  }

  @Override
  @Nullable
  public KdlPsiNodeChildren getNodeChildren() {
    return findChildByClass(KdlPsiNodeChildren.class);
  }

  @Override
  @NotNull
  public List<KdlPsiNodePropOrArg> getNodePropOrArgList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, KdlPsiNodePropOrArg.class);
  }

  @Override
  @Nullable
  public KdlPsiType getType() {
    return findChildByClass(KdlPsiType.class);
  }

}
