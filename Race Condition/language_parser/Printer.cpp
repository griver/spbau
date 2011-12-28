#include "Printer.h"

string indent = "   "; 
int rate = 0;

static void print(string arg, bool needIndent = false, bool endLine = false ) {
    if(needIndent == true)
        for(int i=0; i<rate; i++)
                cout<<indent;
    cout<<arg;
    if(endLine == true )
        cout<<endl;
}

static void printBlock(vector<Operation*>const & block);

static void printTerm(Operation* val) {
    print("Term", 1, 1);
}

static void printError(ErrorOperation* val) {
    print("Error: " + val->message, 1, 1);
}

static void printDecl(Decl* val) {
    print("Global Declaration: " + val->varName, 1, 1);
}

static void printRead(Read* val) {
    print("Read value of " + val->src, 1, 1);
}

static void printWrite(Write* val) {
    print("Write to " + val->dst + "function from", 1, 0);
    for(int i=0; i<val->src.size(); ++i)
        print("  " + val->src[i]); 
    print("", 0, 1);
}

static void printCall(Call* val) {
    print("Call function: " + val->funcName + " with arguments:", 1, 0);
    for(int i=0; i<val->args.size(); ++i)
        print("  " + val->args[i]); 
    print("", 0, 1);
}

static void printLoop(Loop* val) {
    print("Loop", 1,1);
    print("Condition:", 1, 1);
    rate++;
    printBlock(&val->condition);
    rate--;
    print("Loop body:", 1 ,1);
    rate++;
    printBlock(&val->condition);
    rate--;
    print("End of loop", 1, 1);
}

static void printSwitch(Switch* val) {
    print("Switch", 1, 1);
    print("Condition:", 1, 1);
    rate++;
    printBlock(val->condition);
    rate--;
    for(int i=0; i<val->cases.size(); ++i) {
        print("Case:", 1 ,1);
        rate++;
        printBlock(val->condition);
        rate--;
    }
    print("End of switch", 1, 1);
}

static void printFunc(Function* val) {
    print("Function: "+val->name, 0, 1);
    print("Arguments:");
    string tmp1, tmp2;
    for(int i=0;  i<val->args.size(); ++i) {
        if(i != 0)
            tmp1 = ",  ";
        else
            tmp1 = "  ";
      
        if(val->args[i].isRef)
            tmp2 = "(reference)";
        else
            tmp2 = "(value)";
        
        print(tmp1 + val->args[i].name + tmp2);
    }
    print("", 0, 1);
    print("Function Body:", 0, 1);
    rate++;
    printBlock(val->body);
    rate--;
    print("End of function: "++val->name, 0, 1); 
}


static void printBlock(vector<Operation*>const & block) {
    for(int i=0;  i<block.size();  ++i) {
        
        OperationType type = block[i]->type;
        //print()"-----------------------------------------", 1, 1);
        if(type == DECL)
            printDecl(block[i]);
        else if(type == FUNCTION)
            printFunc(block[i]);
        else if(type == READ)
            printRead(block[i]);
        else if(type == WRITE)
            printWrite(block[i]);
        else if(type == CALL)
            printCall(()block[i]);
        else if(type == SWITCH)
            printSwitch((Switch*)block[i]);
        else if(type == LOOP)
            printLoop((Loop*)block[i]);
        else if(type == ERROR)
            printError((ErrorOperation*)block[i]);
        else if(type == TERM)
            printTerm(block[i]);
        else 
            print("Error: unknown operation type", 1, 1);
        //print()"-----------------------------------------", 1, 1);
    }
}

void Printer::operator() (vector<Operation*> const & data) const{
    printBlock(data);
}