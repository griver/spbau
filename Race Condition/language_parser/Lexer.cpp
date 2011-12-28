#include "Lexer.h" 
#include <cctype>
Lexer::Lexer(ifstream  *input): current(Token(DEFAULT_TOKEN, "not started yet")), inFile(input) {};

Lexer::Lexer():current(Token(DEFAULT_TOKEN, "not started yet")), inFile(0) {};

Token const & Lexer::getNext() {
    TokenType type;
    string tmp;
    
    do {
        if(!inFile->eof())
            (*inFile)>>tmp;
        else
        {
            current.type = EOF_TOKEN;
            return current;
        }
    }
    while (!isalnum(tmp[0]) && tmp[0] != '[' && tmp[0] != ']' );
  
    if(tmp == "Read") {
        type = READ_TOKEN;
    }
    else if(tmp == "Write") {
        type = WRITE_TOKEN;
    }
    else if(tmp == "Loop") {
        type = LOOP_TOKEN;
    }
    else if(tmp == "EndLoop") {
        type = END_LOOP_TOKEN;
    }
    else if(tmp == "Switch") {
        type = SWITCH_TOKEN;
    }
    else if(tmp == "EndSwitch") {
        type = END_SWITCH_TOKEN;
    }
    else if(tmp == "Function") {
        type = FUNCTION_TOKEN;
    }
    else if(tmp == "EndFunction") {
        type = END_FUNCTION_TOKEN;
    }
    else if(tmp == "Decl") {
        type = DECL_TOKEN;
    }
    else if(tmp == "Args") {
        type = ARGUMENTS_LIST_TOKEN;
    }
    else if(tmp == "[") {
        type = OPEN_LIST_TOKEN;
    }
    else if(tmp == "]") {
        type = CLOSE_LIST_TOKEN;
    }
    else if(tmp == "Body") {
        type = BODY_TOKEN;
    }
    else if(tmp == "Ref") {
        type = REF_TOKEN;
    }
    else if(tmp == "Case") {
        type = CASE_TOKEN;
    }
    else if(tmp == "Condition") {
        type = CONDITION_TOKEN;
    }
    else if(tmp == "Call") {
        type = CALL_TOKEN;
    }
    else if(isdigit(tmp[0])) {
        type = NUMBER_TOKEN;
    }
    else if(tmp == "Term") {
        type = TERM_TOKEN;
    }
    else if(tmp == "\n") {
        type = END_LINE_TOKEN;
    }
    else {
        type = IDENT_TOKEN;
    }
    
    current.type = type;
    current.value = tmp;
    
    return current;
}

Token const & Lexer::getCurrent() const {
    return current;
}

void Lexer::setInput(ifstream* input) {
    if(this->inFile == 0 || *(this->inFile) != *input)
    this->inFile = input;
}