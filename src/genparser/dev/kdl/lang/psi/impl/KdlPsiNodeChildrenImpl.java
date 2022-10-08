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

public class KdlPsiNodeChildrenImpl extends KdlElementImpl implements KdlPsiNodeChildren {

  public KdlPsiNodeChildrenImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull KdlPsiVisitor visitor) {
    visitor.visitNodeChildren(this);
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
  public List<KdlPsiNodeBlock> getNodeBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, KdlPsiNodeBlock.class);
  }

  @Override
  @NotNull
  public PsiElement getLBrace() {
    return findNotNullChildByType(L_BRACE);
  }

  @Override
  @Nullable
  public PsiElement getRBrace() {
    return findChildByType(R_BRACE);
  }

  @Override
  @Nullable
  public PsiElement getSlashdash() {
    return findChildByType(SLASHDASH);
  }

}
