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

public class KdlPsiTypeImpl extends KdlElementImpl implements KdlPsiType {

  public KdlPsiTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull KdlPsiVisitor visitor) {
    visitor.visitType(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof KdlPsiVisitor) accept((KdlPsiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public KdlPsiIdentifier getIdentifier() {
    return findNotNullChildByClass(KdlPsiIdentifier.class);
  }

  @Override
  @NotNull
  public PsiElement getLParen() {
    return findNotNullChildByType(L_PAREN);
  }

  @Override
  @NotNull
  public PsiElement getRParen() {
    return findNotNullChildByType(R_PAREN);
  }

}
