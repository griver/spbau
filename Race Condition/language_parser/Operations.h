/* 
 * File:   Operation.h
 * Author: griver
 *
 * Created on 4 Декабрь 2011 г., 23:47
 */

#ifndef OPERATION_H
#define	OPERATION_H

#include <fstream>
#include <iostream>
#include <string>
#include <list>
#include <deque>
#include <vector>
#include <fstream>
#include <iostream>
using namespace std;

enum OperationType{
    ERROR,
    READ,
    WRITE,
    CALL,
    DECL,
    LOOP,
    SWITCH,
    FUNCTION,
    TERM        
};


struct Operation {
    Operation();
    Operation(OperationType type);
    virtual vector<string> getTransform(string val);
    OperationType type;
};

struct ErrorOperation : public Operation {
    ErrorOperation();
    ErrorOperation(string error);
    string message;
};

struct Write : public Operation {
    Write();
    vector<string> getTransform(string val);
    vector<string> src;
    string dst;
};

struct Read : public Operation {
    Read();
    vector<string> getTransform(string val);
    string src;
};

struct Decl : public Operation {
    Decl();
    vector<string> getTransform(string val);
    string varName;
};

struct Call : public Operation {
    Call();
    vector<string> getTransform(string val);
    vector <string> args;
    string funcName;
};

struct Loop : public Operation {
    Loop();
    virtual vector<string> getTransform(string val);
    vector<Operation*> condition;
    vector<Operation*> body;
};

struct Switch : public Operation {
    Switch();
    vector<string> getTransform(string val);
    vector<vector<Operation*> > cases;
    vector<Operation*> condition;
};

struct Variable {
    Variable(string name, bool isRef) : name(name), isRef(isRef) {};
    bool isRef;
    string name;
};

struct Function : public Operation {
    Function();
    vector<string> getTransform(string val);
    vector<Operation*> body;
    vector<Variable> args;
    string name;
};


#endif	/* OPERATION_H */

