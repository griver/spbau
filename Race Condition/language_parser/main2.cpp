
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

#include "Parser.h"

void read(string filename)
{	
    ifstream inFile(filename.c_str());       
    Parser* parser = new Parser(new Lexer(), new ConcreteReader());
    parser->parseSource(&inFile);
    parser->writeAll();
}



int main(int argc, char *argv[]) {
    
    switch( argc) {
    case 1:
        cerr<< "You must enter filname"<<endl;
        break;
    case 2:
        read(argv[1]);
        break;
    default: 
        cerr<<"Incorrect arguments"<<endl;
        break;
    }
    //string filename = "samples/01";
    //read(filename);
    return 0;
}