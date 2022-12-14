// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import dev.kdl.lang.psi.ext.KdlElement;
import dev.kdl.lang.psi.ext.KdlCommentableItem;

public class KdlPsiVisitor extends PsiElementVisitor {

  public void visitArg(@NotNull KdlPsiArg o) {
    visitKdlElement(o);
  }

  public void visitBoolean(@NotNull KdlPsiBoolean o) {
    visitKdlElement(o);
  }

  public void visitEscline(@NotNull KdlPsiEscline o) {
    visitKdlElement(o);
  }

  public void visitIdentifier(@NotNull KdlPsiIdentifier o) {
    visitKdlElement(o);
  }

  public void visitLiteral(@NotNull KdlPsiLiteral o) {
    visitKdlElement(o);
  }

  public void visitNodeBlock(@NotNull KdlPsiNodeBlock o) {
    visitKdlCommentableItem(o);
  }

  public void visitNodeChildren(@NotNull KdlPsiNodeChildren o) {
    visitKdlCommentableItem(o);
  }

  public void visitNodePropOrArg(@NotNull KdlPsiNodePropOrArg o) {
    visitKdlCommentableItem(o);
  }

  public void visitNodeTerminator(@NotNull KdlPsiNodeTerminator o) {
    visitKdlElement(o);
  }

  public void visitNull(@NotNull KdlPsiNull o) {
    visitKdlElement(o);
  }

  public void visitNumber(@NotNull KdlPsiNumber o) {
    visitKdlElement(o);
  }

  public void visitProp(@NotNull KdlPsiProp o) {
    visitKdlElement(o);
  }

  public void visitString(@NotNull KdlPsiString o) {
    visitKdlElement(o);
  }

  public void visitType(@NotNull KdlPsiType o) {
    visitKdlElement(o);
  }

  public void visitValue(@NotNull KdlPsiValue o) {
    visitKdlElement(o);
  }

  public void visitKdlCommentableItem(@NotNull KdlCommentableItem o) {
    visitElement(o);
  }

  public void visitKdlElement(@NotNull KdlElement o) {
    visitElement(o);
  }

}
