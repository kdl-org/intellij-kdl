// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.psi.ext;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.kdl.lang.lexer.KdlElementType;
import dev.kdl.lang.lexer.KdlTokenType;
import dev.kdl.lang.psi.impl.*;

public interface KdlElementTypes {

  IElementType ARG = new KdlElementType("ARG");
  IElementType BOOLEAN = new KdlElementType("BOOLEAN");
  IElementType ESCLINE = new KdlElementType("ESCLINE");
  IElementType IDENTIFIER = new KdlElementType("IDENTIFIER");
  IElementType LITERAL = new KdlElementType("LITERAL");
  IElementType NODE_BLOCK = new KdlElementType("NODE_BLOCK");
  IElementType NODE_CHILDREN = new KdlElementType("NODE_CHILDREN");
  IElementType NODE_PROP_OR_ARG = new KdlElementType("NODE_PROP_OR_ARG");
  IElementType NODE_TERMINATOR = new KdlElementType("NODE_TERMINATOR");
  IElementType NULL = new KdlElementType("NULL");
  IElementType NUMBER = new KdlElementType("NUMBER");
  IElementType PROP = new KdlElementType("PROP");
  IElementType STRING = new KdlElementType("STRING");
  IElementType TYPE = new KdlElementType("TYPE");
  IElementType VALUE = new KdlElementType("VALUE");

  IElementType BACKSLASH = new KdlTokenType("\\");
  IElementType BARE_IDENTIFIER = new KdlTokenType("BARE_IDENTIFIER");
  IElementType BINARY_LITERAL = new KdlTokenType("BINARY_LITERAL");
  IElementType COMMA = new KdlTokenType(",");
  IElementType DECIMAL_LITERAL = new KdlTokenType("DECIMAL_LITERAL");
  IElementType EQ = new KdlTokenType("=");
  IElementType FALSE_LITERAL = new KdlTokenType("false");
  IElementType HEX_LITERAL = new KdlTokenType("HEX_LITERAL");
  IElementType LESS_THEN = new KdlTokenType("<");
  IElementType L_BRACE = new KdlTokenType("{");
  IElementType L_BRACK = new KdlTokenType("[");
  IElementType L_PAREN = new KdlTokenType("(");
  IElementType MORE_THEN = new KdlTokenType(">");
  IElementType MULTI_LINE_COMMENT = new KdlTokenType("MULTI_LINE_COMMENT");
  IElementType NEWLINE = new KdlTokenType("NEWLINE");
  IElementType NULL_LITERAL = new KdlTokenType("null");
  IElementType OCTAL_LITERAL = new KdlTokenType("OCTAL_LITERAL");
  IElementType QUOTE = new KdlTokenType("\"");
  IElementType RAW_STRING_LITERAL = new KdlTokenType("RAW_STRING_LITERAL");
  IElementType R_BRACE = new KdlTokenType("}");
  IElementType R_BRACK = new KdlTokenType("]");
  IElementType R_PAREN = new KdlTokenType(")");
  IElementType SEMI = new KdlTokenType(";");
  IElementType SINGLE_LINE_COMMENT = new KdlTokenType("SINGLE_LINE_COMMENT");
  IElementType SLASH = new KdlTokenType("/");
  IElementType SLASHDASH = new KdlTokenType("/-");
  IElementType STRING_LITERAL = new KdlTokenType("STRING_LITERAL");
  IElementType TRUE_LITERAL = new KdlTokenType("true");
  IElementType UNICODE_SPACE = new KdlTokenType("UNICODE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARG) {
        return new KdlPsiArgImpl(node);
      }
      else if (type == BOOLEAN) {
        return new KdlPsiBooleanImpl(node);
      }
      else if (type == ESCLINE) {
        return new KdlPsiEsclineImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new KdlPsiIdentifierImpl(node);
      }
      else if (type == LITERAL) {
        return new KdlPsiLiteralImpl(node);
      }
      else if (type == NODE_BLOCK) {
        return new KdlPsiNodeBlockImpl(node);
      }
      else if (type == NODE_CHILDREN) {
        return new KdlPsiNodeChildrenImpl(node);
      }
      else if (type == NODE_PROP_OR_ARG) {
        return new KdlPsiNodePropOrArgImpl(node);
      }
      else if (type == NODE_TERMINATOR) {
        return new KdlPsiNodeTerminatorImpl(node);
      }
      else if (type == NULL) {
        return new KdlPsiNullImpl(node);
      }
      else if (type == NUMBER) {
        return new KdlPsiNumberImpl(node);
      }
      else if (type == PROP) {
        return new KdlPsiPropImpl(node);
      }
      else if (type == STRING) {
        return new KdlPsiStringImpl(node);
      }
      else if (type == TYPE) {
        return new KdlPsiTypeImpl(node);
      }
      else if (type == VALUE) {
        return new KdlPsiValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
