#include "Parser.h"
//генерился снаружи

Parser::Parser(Lexer* lexer, ConcreteReader * reader): lexer(lexer), reader(reader),funcs(vector<Function*>(0)), vars(vector<Decl*>(0)), data(vector<Operation*>(0)){
    //Functio
    reader->setLexer(lexer);
}
 //генерился снаружи
void Parser::parseSource(ifstream* inFile) {
    lexer->setInput(inFile);
    Token token = lexer->getNext();
    while(token.type != EOF_TOKEN) {
        if(token.type == FUNCTION_TOKEN) {
            //funcs.push_back((Function*)reader->readFunc());
            data.push_back(reader->readFunc());
        }
        else if(token.type == DECL_TOKEN) {
           // vars.push_back((Decl*)reader->readDecl());
            data.push_back(reader->readFunc());
        }
        else {
            cerr<<"All broken!(";
            break;
        }
        token = lexer->getNext();
    }
}

void Parser::writeAll() {
    for(int i=0; i<funcs.size(); i++)
    {
        cout<<"FUNCTION "<<i+1<<endl;
        cout<<"    name: "<<funcs[i]->name<<endl;
        cout<<"    args: ";
        for(int j=0; j<funcs[i]->args.size(); ++j) {
            cout<<funcs[i]->args[j].name;
            if(funcs[i]->args[j].isRef)
                cout<<" is reference,  ";
            else
                cout<<",  ";
        }
        cout<<endl;
        cout<<"number of operations: "<<funcs[i]->body.size()<<endl<<endl<<endl;

    }
    
    cout<<"Global Variables are: ";
    for(int i=0; i < vars.size(); i ++ )
        cout<<vars[i]->varName<<"  ";
    
    
}

vector<Operation*>const & Parser::getData() const {
    return data;
}

