/* 
 * File:   Parser.h
 * Author: griver
 *
 * Created on 4 Декабрь 2011 г., 23:47
 */

#ifndef PARSER_H
#define	PARSER_H
#include "ConcreteReader.h"


class Parser {
private:
    vector<Function*>  funcs;
    vector<Decl*> vars;
    vector<Operation*> data; 
    ConcreteReader *reader;
    Lexer *lexer;
public:
    Parser(Lexer *lexer, ConcreteReader *reader);
    void parseSource(ifstream* inFile);
    void getFunction(string namefun);
    void setLexer(Lexer *lexer);
    void setReader(ConcreteReader *reader);
    void writeAll();
    vector<Operation*>const &  getData() const;
};


#endif	/* PARSER_H */

