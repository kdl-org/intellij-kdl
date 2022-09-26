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

public class KdlPsiEsclineImpl extends KdlElementImpl implements KdlPsiEscline {

  public KdlPsiEsclineImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull KdlPsiVisitor visitor) {
    visitor.visitEscline(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof KdlPsiVisitor) accept((KdlPsiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getNewline() {
    return findNotNullChildByType(NEWLINE);
  }

}
