/* 
 * File:   main.cpp
 * Author: griver
 *
 * Created on 13 Ноябрь 2011 г., 0:10
 */

#include <iostream>
#include <vector>
#include "memory_allocator.h"
using namespace std;


void fill_block( my_ptr& p, int size, char c)
{
    for(int i=0; i<size-1; i++)
        ((char*)p.get())[i]= c;
    ((char*)p.get())[size-1]= '\n';
}

int main(int argc, char** argv) 
{
    init();
    my_ptr p1 = my_alloc(5);
    fill_block(p1, 5, 'A');
    my_ptr p2 = my_alloc(8);
    fill_block(p2, 8, 'B');
    my_ptr p3 = my_alloc(6);
    fill_block(p3, 6, 'C');
    cout<<"-----Insert blocks: A(5), B(8), C(6)----------------"<<endl;
    print(0, 50);
    my_free(p2);
    my_ptr p4 = my_alloc(4);
    fill_block(p4, 4, 'D');
    cout<<"-----Remove block B(8). Insert block D(4)--------"<<endl;
    print(0, 50);
    my_defragmentation();
    cout<<"-----Memory after defragmentation----------"<<endl;
    print(0, 50);
    cout<<"number of MCB: "<<*get_count()<<endl;
    return 0;
}

