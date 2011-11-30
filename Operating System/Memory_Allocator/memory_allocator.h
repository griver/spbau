#ifndef MEMORY_ALLOCATOR_H
#define	MEMORY_ALLOCATOR_H

#include <iostream>
#include <string.h>
using namespace std;

//----MCB------------------------------------------
struct MCB
{
    bool used;
    MCB* next;
    MCB* prev;
    
    int begin;
    int size;
    
};
//-------------------------------------------------

//---DATA------------------------------------------
const int MEMSIZE= 2048;
static char data[MEMSIZE+sizeof(MCB)+sizeof(int)];
//-------------------------------------------------

//----MY_PTR---------------------------------------
struct my_ptr
{
    char* _data;  
    MCB * mcb;
public:
    my_ptr(char * d, MCB* mcb);
    void* get();
};
//------------------------------------------------

//------FUNCTIONALITY-----------------------------
my_ptr my_alloc(int size);
        
void my_free(my_ptr& ptr );    

void my_defragmentation();

void init();

int* get_count();

MCB* get_first_mcb();

void insert_mcb(MCB* prev, MCB* new_mcb,  MCB* next);

void remove(MCB* mcb);

void print(int begin, int end);

void fill(char sym);
//--------------------------------------------
#endif	/* MEMORY_ALLOCATOR_H */
