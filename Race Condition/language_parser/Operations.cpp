#include "Operations.h"


//------------Operation-------------------------------------------
 Operation::Operation(): type(TERM){};
 
Operation::Operation( OperationType type): type(type){};

vector<string> Operation::getTransform(string val){
    vector<string> res(0);
    res.push_back("Term"); 
    return res;
}
//----------------------------------------------------------------

//----Error------------------------------------------------------
 ErrorOperation::ErrorOperation(): Operation(ERROR), message("Error!") { };
 
 ErrorOperation::ErrorOperation(string error):Operation(ERROR), message(error){};
 //---------------------------------------------------------------
 
//----Write------------------------------------------------------
Write::Write():Operation(WRITE){};

vector<string> Write::getTransform(string var) {
    vector<string> res(0);
    bool inSrc = false;
    for(int i=0; i<src.size(); i++)
        if(src[i] == var) 
            inSrc = true;
    
    if (inSrc) {
         res.push_back("read");
         if(dst == var) {
             res.push_back("var");         //Подумать. всегда ли когда read следует var
             res.push_back("write");
         }//if(var in src && var in dst)
    }//if(var in src)
    else if(dst == var) {
        res.push_back("write");
    } // если в переменную просто записывают значение
    else {
        res.push_back("term");
    } // если переменной нет.
    return res;
}
//---------------------------------------------------------------------

//------DECL-----------------------------------------------------------
Decl::Decl():Operation(DECL){};

vector<string> Decl::getTransform(string val) {
    return vector<string>();
}
//---------------------------------------------------------------------

//------SWITCH---------------------------------------------------------
Switch::Switch():Operation(SWITCH){};

vector<string> Switch::getTransform(string val) {
    return vector<string>();
}
//------READ-----------------------------------------------------------
Read::Read():Operation(READ){};

vector<string> Read::getTransform(string val) {
return vector<string>();
}
//---------------------------------------------------------------------

//---------CALL--------------------------------------------------------
Call::Call():Operation(CALL){};

vector<string> Call::getTransform(string val) {return vector<string>(0);}
//---------------------------------------------------------------------

//---------LOOP--------------------------------------------------------
Loop::Loop():Operation(LOOP){};

vector<string> Loop::getTransform(string val) {return vector<string>(0);}
//---------------------------------------------------------------------

//---------FUNCTION----------------------------------------------------
Function::Function():Operation(FUNCTION){};

vector<string> Function::getTransform(string val) {return vector<string>(0);}
//---------------------------------------------------------------------
