/* 
 * File:   Lexer.h
 * Author: griver
 *
 * Created on 7 Декабрь 2011 г., 0:27
 */

#ifndef LEXER_H
#define	LEXER_H

#include "Operations.h"
#include "Tokens.h"

class Lexer {
private:
    ifstream *inFile;
    Token current;
    
public:
    explicit Lexer(ifstream *input);
    explicit Lexer();
    Token const & getNext();
    Token const & getCurrent() const;
    void setInput(ifstream *input);
    //~Lexer();
};




#endif	/* LEXER_H */

