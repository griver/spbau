#include <iterator>
#include <list>
#include <iostream>
#include "skip_iterator.h"

int main() {
    std::list<int> ls(0);
    //ls.assign(4 , 5);
    ls.push_back(2);
    ls.push_back(0);
    ls.push_back(4);
    ls.push_back(0);
    ls.push_back(8);
    
    std::list<int>::iterator iter = ls.begin(); 
    /*for(iter; iter != ls.end(); ++iter) {
        std::cout<<": "<<(*iter)<<std::endl;
    }*/

    skip_iterator< std::list<int>::iterator > sIt(ls.begin(), ls.end(), 0);
    
    skip_iterator< std::list<int>::iterator > endIt(ls.end(), ls.end(), 0);    
    std::cout<<*sIt<<"  "<<std::endl;
    for(sIt; sIt != endIt; ++sIt) {
        std::cout<<": "<<(*sIt)<<std::endl;
    }

    return 0;
}
