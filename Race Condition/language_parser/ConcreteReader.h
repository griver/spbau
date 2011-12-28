/* 
 * File:   ConcreteReader.h
 * Author: griver
 *
 * Created on 5 Декабрь 2011 г., 11:10
 */

#ifndef CONCRETEREADER_H
#define	CONCRETEREADER_H

#include "Operations.h"
#include "Lexer.h"


class ConcreteReader {
private:
    Lexer * lexer;
public:
    explicit ConcreteReader();
    Operation* readFunc() const; 
    Operation* readWrite() const; //написанно
    Operation* readRead() const;  //написанно
    Operation* readLoop() const;  //
    Operation* readSwitch() const;//.....
    Operation* readDecl() const;  //написанно
    Operation* readCall() const;  //
    Operation* readTerm() const;  //
    void setLexer(Lexer *lexer);
};


#endif	/* CONCRETEREADER_H */

