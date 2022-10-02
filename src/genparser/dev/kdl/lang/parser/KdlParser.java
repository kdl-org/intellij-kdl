// This is a generated file. Not intended for manual editing.
package dev.kdl.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static dev.kdl.lang.psi.ext.KdlElementTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class KdlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return File(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // top-level-node-list
  static boolean File(PsiBuilder builder_, int level_) {
    return top_level_node_list(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // value
  public static boolean arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG, "<arg>");
    result_ = value(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // 'true' | 'false'
  public static boolean boolean_$(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "boolean_$")) return false;
    if (!nextTokenIs(builder_, "<boolean $>", FALSE_LITERAL, TRUE_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BOOLEAN, "<boolean $>");
    result_ = consumeToken(builder_, TRUE_LITERAL);
    if (!result_) result_ = consumeToken(builder_, FALSE_LITERAL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '\' ws* NEWLINE
  public static boolean escline(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "escline")) return false;
    if (!nextTokenIs(builder_, BACKSLASH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BACKSLASH);
    result_ = result_ && escline_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, NEWLINE);
    exit_section_(builder_, marker_, ESCLINE, result_);
    return result_;
  }

  // ws*
  private static boolean escline_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "escline_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ws(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "escline_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // string | BARE_IDENTIFIER
  public static boolean identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, IDENTIFIER, "<identifier>");
    result_ = string(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, BARE_IDENTIFIER);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // NEWLINE | ws
  static boolean linespace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "linespace")) return false;
    if (!nextTokenIs(builder_, "", NEWLINE, UNICODE_SPACE)) return false;
    boolean result_;
    result_ = consumeToken(builder_, NEWLINE);
    if (!result_) result_ = ws(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // string | number | boolean | null
  public static boolean literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LITERAL, "<literal>");
    result_ = string(builder_, level_ + 1);
    if (!result_) result_ = number(builder_, level_ + 1);
    if (!result_) result_ = boolean_$(builder_, level_ + 1);
    if (!result_) result_ = null_$(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ('/-' node-space*)? type? identifier (node-space+ node-prop-or-arg)* (node-space* node-children ws*)? node-space*
  public static boolean node_block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NODE_BLOCK, "<node block>");
    result_ = node_block_0(builder_, level_ + 1);
    result_ = result_ && node_block_1(builder_, level_ + 1);
    result_ = result_ && identifier(builder_, level_ + 1);
    result_ = result_ && node_block_3(builder_, level_ + 1);
    result_ = result_ && node_block_4(builder_, level_ + 1);
    result_ = result_ && node_block_5(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ('/-' node-space*)?
  private static boolean node_block_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_0")) return false;
    node_block_0_0(builder_, level_ + 1);
    return true;
  }

  // '/-' node-space*
  private static boolean node_block_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SLASHDASH);
    result_ = result_ && node_block_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // node-space*
  private static boolean node_block_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_0_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!node_space(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_block_0_0_1", pos_)) break;
    }
    return true;
  }

  // type?
  private static boolean node_block_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_1")) return false;
    type(builder_, level_ + 1);
    return true;
  }

  // (node-space+ node-prop-or-arg)*
  private static boolean node_block_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!node_block_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_block_3", pos_)) break;
    }
    return true;
  }

  // node-space+ node-prop-or-arg
  private static boolean node_block_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = node_block_3_0_0(builder_, level_ + 1);
    result_ = result_ && node_prop_or_arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // node-space+
  private static boolean node_block_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_3_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = node_space(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!node_space(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_block_3_0_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (node-space* node-children ws*)?
  private static boolean node_block_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_4")) return false;
    node_block_4_0(builder_, level_ + 1);
    return true;
  }

  // node-space* node-children ws*
  private static boolean node_block_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = node_block_4_0_0(builder_, level_ + 1);
    result_ = result_ && node_children(builder_, level_ + 1);
    result_ = result_ && node_block_4_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // node-space*
  private static boolean node_block_4_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_4_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!node_space(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_block_4_0_0", pos_)) break;
    }
    return true;
  }

  // ws*
  private static boolean node_block_4_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_4_0_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ws(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_block_4_0_2", pos_)) break;
    }
    return true;
  }

  // node-space*
  private static boolean node_block_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_block_5")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!node_space(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_block_5", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ('/-' node-space*)? '{' node-children-inner? '}'
  public static boolean node_children(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children")) return false;
    if (!nextTokenIs(builder_, "<node children>", L_BRACE, SLASHDASH)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NODE_CHILDREN, "<node children>");
    result_ = node_children_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, node_children_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ('/-' node-space*)?
  private static boolean node_children_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children_0")) return false;
    node_children_0_0(builder_, level_ + 1);
    return true;
  }

  // '/-' node-space*
  private static boolean node_children_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SLASHDASH);
    result_ = result_ && node_children_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // node-space*
  private static boolean node_children_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children_0_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!node_space(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_children_0_0_1", pos_)) break;
    }
    return true;
  }

  // node-children-inner?
  private static boolean node_children_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children_2")) return false;
    node_children_inner(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // !'}' node-list &'}'
  static boolean node_children_inner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children_inner")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = node_children_inner_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, node_list(builder_, level_ + 1));
    result_ = pinned_ && node_children_inner_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, KdlParser::node_children_recover);
    return result_ || pinned_;
  }

  // !'}'
  private static boolean node_children_inner_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children_inner_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // &'}'
  private static boolean node_children_inner_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children_inner_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // !'}'
  static boolean node_children_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_children_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // linespace* (node-block node-terminator? linespace*)*
  static boolean node_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_list")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = node_list_0(builder_, level_ + 1);
    result_ = result_ && node_list_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // linespace*
  private static boolean node_list_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_list_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!linespace(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_list_0", pos_)) break;
    }
    return true;
  }

  // (node-block node-terminator? linespace*)*
  private static boolean node_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!node_list_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_list_1", pos_)) break;
    }
    return true;
  }

  // node-block node-terminator? linespace*
  private static boolean node_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = node_block(builder_, level_ + 1);
    result_ = result_ && node_list_1_0_1(builder_, level_ + 1);
    result_ = result_ && node_list_1_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // node-terminator?
  private static boolean node_list_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_list_1_0_1")) return false;
    node_terminator(builder_, level_ + 1);
    return true;
  }

  // linespace*
  private static boolean node_list_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_list_1_0_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!linespace(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_list_1_0_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ('/-' node-space*)? (prop | arg)
  public static boolean node_prop_or_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_prop_or_arg")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NODE_PROP_OR_ARG, "<node prop or arg>");
    result_ = node_prop_or_arg_0(builder_, level_ + 1);
    result_ = result_ && node_prop_or_arg_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ('/-' node-space*)?
  private static boolean node_prop_or_arg_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_prop_or_arg_0")) return false;
    node_prop_or_arg_0_0(builder_, level_ + 1);
    return true;
  }

  // '/-' node-space*
  private static boolean node_prop_or_arg_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_prop_or_arg_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SLASHDASH);
    result_ = result_ && node_prop_or_arg_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // node-space*
  private static boolean node_prop_or_arg_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_prop_or_arg_0_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!node_space(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_prop_or_arg_0_0_1", pos_)) break;
    }
    return true;
  }

  // prop | arg
  private static boolean node_prop_or_arg_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_prop_or_arg_1")) return false;
    boolean result_;
    result_ = prop(builder_, level_ + 1);
    if (!result_) result_ = arg(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // ws* escline ws* | ws+
  static boolean node_space(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_space")) return false;
    if (!nextTokenIs(builder_, "", BACKSLASH, UNICODE_SPACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = node_space_0(builder_, level_ + 1);
    if (!result_) result_ = node_space_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ws* escline ws*
  private static boolean node_space_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_space_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = node_space_0_0(builder_, level_ + 1);
    result_ = result_ && escline(builder_, level_ + 1);
    result_ = result_ && node_space_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ws*
  private static boolean node_space_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_space_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ws(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_space_0_0", pos_)) break;
    }
    return true;
  }

  // ws*
  private static boolean node_space_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_space_0_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ws(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_space_0_2", pos_)) break;
    }
    return true;
  }

  // ws+
  private static boolean node_space_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_space_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ws(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!ws(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "node_space_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NEWLINE | ';' | <<eof>>
  static boolean node_terminator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "node_terminator")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEWLINE);
    if (!result_) result_ = consumeToken(builder_, SEMI);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'null'
  public static boolean null_$(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "null_$")) return false;
    if (!nextTokenIs(builder_, NULL_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NULL_LITERAL);
    exit_section_(builder_, marker_, NULL, result_);
    return result_;
  }

  /* ********************************************************** */
  // DECIMAL_LITERAL | HEX_LITERAL | OCTAL_LITERAL | BINARY_LITERAL
  public static boolean number(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "number")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NUMBER, "<number>");
    result_ = consumeToken(builder_, DECIMAL_LITERAL);
    if (!result_) result_ = consumeToken(builder_, HEX_LITERAL);
    if (!result_) result_ = consumeToken(builder_, OCTAL_LITERAL);
    if (!result_) result_ = consumeToken(builder_, BINARY_LITERAL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // identifier '=' value
  public static boolean prop(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prop")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PROP, "<prop>");
    result_ = identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQ);
    result_ = result_ && value(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // RAW_STRING_LITERAL | STRING_LITERAL
  public static boolean string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string")) return false;
    if (!nextTokenIs(builder_, "<string>", RAW_STRING_LITERAL, STRING_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRING, "<string>");
    result_ = consumeToken(builder_, RAW_STRING_LITERAL);
    if (!result_) result_ = consumeToken(builder_, STRING_LITERAL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // node-list
  public static boolean top_level_node_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_node_list")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TOP_LEVEL_NODE_LIST, "<top level node list>");
    result_ = node_list(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '(' identifier ')'
  public static boolean type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // type? literal
  public static boolean value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "value")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VALUE, "<value>");
    result_ = value_0(builder_, level_ + 1);
    result_ = result_ && literal(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // type?
  private static boolean value_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "value_0")) return false;
    type(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // UNICODE_SPACE
  static boolean ws(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, UNICODE_SPACE);
  }

}
