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

public class KdlPsiBooleanImpl extends KdlElementImpl implements KdlPsiBoolean {

  public KdlPsiBooleanImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull KdlPsiVisitor visitor) {
    visitor.visitBoolean(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof KdlPsiVisitor) accept((KdlPsiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getFalseLiteral() {
    return findChildByType(FALSE_LITERAL);
  }

  @Override
  @Nullable
  public PsiElement getTrueLiteral() {
    return findChildByType(TRUE_LITERAL);
  }

}
