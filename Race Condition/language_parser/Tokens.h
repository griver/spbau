/* 
 * File:   Tokens.h
 * Author: griver
 *
 * Created on 7 Декабрь 2011 г., 0:31
 */

#ifndef TOKENS_H
#define	TOKENS_H
#include <string>

enum TokenType {
    READ_TOKEN,
    WRITE_TOKEN,
    LOOP_TOKEN,
    END_LOOP_TOKEN,
    CALL_TOKEN,
    SWITCH_TOKEN,
    END_SWITCH_TOKEN,
    FUNCTION_TOKEN,
    END_FUNCTION_TOKEN,
    DECL_TOKEN,
    IDENT_TOKEN,
    REF_TOKEN,
    OPEN_LIST_TOKEN,
    CLOSE_LIST_TOKEN,
    BODY_TOKEN,
    CASE_TOKEN,
    NUMBER_TOKEN,
    OPERATOR_TOKEN,
    CONDITION_TOKEN,
    RETURN_VAL_TOKEN,
    ARGUMENTS_LIST_TOKEN,
    TERM_TOKEN,
    DEFAULT_TOKEN,
    END_LINE_TOKEN,
    EOF_TOKEN
};
  
struct Token {
    std::string value;
    TokenType type;
    explicit Token(TokenType type, std::string value = ""): type(type), value(value){};
    
};
    

#endif	/* TOKENS_H */

