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

public class KdlPsiLiteralImpl extends KdlElementImpl implements KdlPsiLiteral {

  public KdlPsiLiteralImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull KdlPsiVisitor visitor) {
    visitor.visitLiteral(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof KdlPsiVisitor) accept((KdlPsiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public KdlPsiBoolean getBoolean() {
    return findChildByClass(KdlPsiBoolean.class);
  }

  @Override
  @Nullable
  public KdlPsiNull getNull() {
    return findChildByClass(KdlPsiNull.class);
  }

  @Override
  @Nullable
  public KdlPsiNumber getNumber() {
    return findChildByClass(KdlPsiNumber.class);
  }

  @Override
  @Nullable
  public KdlPsiString getString() {
    return findChildByClass(KdlPsiString.class);
  }

  @Override
  @Nullable
  public PsiElement getBareIdentifier() {
    return findChildByType(BARE_IDENTIFIER);
  }

}
