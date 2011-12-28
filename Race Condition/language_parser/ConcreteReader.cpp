#include "ConcreteReader.h"

ConcreteReader::ConcreteReader(){};

Operation* ConcreteReader::readRead() const{
    Token token = lexer->getNext();
    
    if(token.type == IDENT_TOKEN || token.type == TERM_TOKEN) {
        Read* read = new Read();
        read->src = token.value;
        return read;
    }
    else 
        return new ErrorOperation("invalid type of read operation's argument");//error;
}


Operation* ConcreteReader::readWrite() const {
    Token token = lexer->getNext();
    Write *write = new Write();  
    if(token.type != IDENT_TOKEN)
        return new ErrorOperation("missing Distination");
   
    write->dst = token.value;
    
    token = lexer->getNext();
    if(token.type != OPEN_LIST_TOKEN)
        return new ErrorOperation("Write invalid syntax");
    
    token = lexer->getNext();
    while(token.type == IDENT_TOKEN) {
        write->src.push_back(token.value);
        token = lexer->getNext();
    }
    
    if(token.type != CLOSE_LIST_TOKEN)
        return new ErrorOperation("Invalid list syntax");
    return write;
}       

Operation* ConcreteReader::readDecl() const {
    Token token = lexer->getNext();  
    if(token.type != IDENT_TOKEN)
        return new ErrorOperation("Decl without argument");
    Decl* decl = new Decl();
    decl->varName = token.value;
    return decl;
}

Operation* ConcreteReader::readSwitch() const {
    Token token = lexer->getNext();
    Switch *swch =new Switch();
    if(token.type != CONDITION_TOKEN )
        return new ErrorOperation("Switch syntax error");
    
    token = lexer->getNext();
    while(token.type != CASE_TOKEN && token.type != END_SWITCH_TOKEN)
    {
        if(token.type == READ_TOKEN) {
            swch->condition.push_back(this->readRead());
        }
        else if(token.type == WRITE_TOKEN) {
            swch->condition.push_back(this->readWrite());
        }
        else if(token.type == DECL_TOKEN) {
            swch->condition.push_back(this->readDecl());
        }
        else if(token.type == CALL_TOKEN) {
            swch->condition.push_back(this->readCall());
        }
        token = lexer->getNext();
    }
    
    if(token.type == END_SWITCH_TOKEN)
        return new ErrorOperation("Switch missing cases");
    
    int cur = 0; 
    while(token.type !=END_SWITCH_TOKEN ) {
        if(token.type == CASE_TOKEN) {
            swch->cases.push_back(vector<Operation*>(0));
            cur = swch->cases.size()-1;
        }
        else if(token.type == READ_TOKEN) {
            swch->cases[cur].push_back(this->readRead());
        }
        else if(token.type == WRITE_TOKEN) {
            swch->cases[cur].push_back(this->readWrite());
        }
        else if(token.type == DECL_TOKEN) {
            swch->cases[cur].push_back(this->readDecl());
        }
        else if(token.type == CALL_TOKEN) {
            swch->cases[cur].push_back(this->readCall());
        }
        else if(token.type == LOOP_TOKEN) {
            swch->cases[cur].push_back(this->readLoop());
        }
        token = lexer->getNext();
    }
                               
    return swch;        //нужно еще одно считывание.
}

Operation* ConcreteReader::readCall() const {
    Token token = lexer->getNext();
    Call *call = new Call();  
    if(token.type != IDENT_TOKEN)
        return new ErrorOperation("missing Distination");
   
    call->funcName = token.value;
    
    token = lexer->getNext();
    if(token.type != OPEN_LIST_TOKEN)
        return new ErrorOperation("Call invalid syntax");
    
    token = lexer->getNext();
    while(token.type == IDENT_TOKEN) {
        call->args.push_back(token.value);
        token = lexer->getNext();
    }
    
    if(token.type != CLOSE_LIST_TOKEN)
        return new ErrorOperation("Invalid list syntax");
    return call;
}

Operation* ConcreteReader::readLoop() const {
    Token token = lexer->getNext();
    Loop *loop = new Loop();
    
    if(token.type != CONDITION_TOKEN)
        return new ErrorOperation("Incalid Loop Syntax. Missing Condition");
    
    token = lexer->getNext();
    
    while(token.type != BODY_TOKEN)
    {
        if(token.type == READ_TOKEN) {
            loop->condition.push_back(this->readRead());
        }
        else if(token.type == WRITE_TOKEN) {
            loop->condition.push_back(this->readWrite());
        }
        else if(token.type == DECL_TOKEN) {
            loop->condition.push_back(this->readDecl());
        }
        else if(token.type == CALL_TOKEN) {
            loop->condition.push_back(this->readCall());
        }
        token = lexer->getNext();
    }
    
    token = lexer->getNext();
    
    while(token.type != END_LOOP_TOKEN)
    {
        if(token.type == READ_TOKEN) {
            loop->body.push_back(this->readRead());
        }
        else if(token.type == WRITE_TOKEN) {
            loop->body.push_back(this->readWrite());
        }
        else if(token.type == DECL_TOKEN) {
            loop->body.push_back(this->readDecl());
        }
        else if(token.type == CALL_TOKEN) {
            loop->body.push_back(this->readCall());
        }
        else if(token.type == SWITCH_TOKEN) {
            loop->body.push_back(this->readSwitch());
        }
        else if(token.type == LOOP_TOKEN) {
            loop->body.push_back(this->readLoop());
        }
        token = lexer->getNext();
    }
}

Operation* ConcreteReader::readTerm() const {
    return new Operation();
}

Operation* ConcreteReader::readFunc() const {
    Token token = lexer->getNext();
    Function* func = new Function();
    
    if(token.type != IDENT_TOKEN )
        return new ErrorOperation("Function Without Name!");
    
    func->name = token.value;
    
    token = lexer->getNext();
    if(token.type != ARGUMENTS_LIST_TOKEN)
        return new ErrorOperation("Error Function Sintax");
    token = lexer->getNext();
    if(token.type != OPEN_LIST_TOKEN)
        return new ErrorOperation("Error Function Sintax: invalid list");
    
    token = lexer->getNext();
    while(token.type == REF_TOKEN || token.type == IDENT_TOKEN) {
        bool isRef = false;
        if(token.type == REF_TOKEN) {
            isRef = true;
            token = lexer->getNext();
        }
        func->args.push_back(Variable(token.value, isRef));

        token = lexer->getNext();        
    }
    
    if(token.type != CLOSE_LIST_TOKEN)
        return new ErrorOperation("Error Function Sintax: invalid list");
    
    token = lexer->getNext();
    if(token.type != BODY_TOKEN)
        return new ErrorOperation("Error Function Sintax: Missing Body");
    
    token = lexer->getNext();
    while(token.type != END_FUNCTION_TOKEN) {
           if(token.type == READ_TOKEN) {
            func->body.push_back(this->readRead());
        }
        else if(token.type == WRITE_TOKEN) {
            func->body.push_back(this->readWrite());
        }
        else if(token.type == DECL_TOKEN) {
            func->body.push_back(this->readDecl());
        }
        else if(token.type == CALL_TOKEN) {
            func->body.push_back(this->readCall());
        }
        else if(token.type == SWITCH_TOKEN) {
            func->body.push_back(this->readSwitch());
        }
        else if(token.type == LOOP_TOKEN) {
            func->body.push_back(this->readLoop());
        }
        token = lexer->getNext();
    }
    return func;
}

void ConcreteReader::setLexer(Lexer* lexer) {
    this->lexer = lexer;
}
