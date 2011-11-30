#include "memory_allocator.h"

//-------FUNCTIONALITY-------------------------------------------
void print(int begin, int end)
{
     for(int i=begin; i<end; ++i)
        cout<<data[i];
    cout<<endl;    
}

void fill_data(char sym)
{
    for(int i=0; i<MEMSIZE; ++i)
        data[i]=sym;
}


int* get_count()
{
    return (int*)(data+ MEMSIZE + sizeof(MCB));
}

MCB* get_first_mcb()
{
    return (MCB*)(data+ MEMSIZE);
}

void insert_mcb(MCB* prev, MCB* new_mcb,  MCB* next )
{
    new_mcb->prev = prev;
    new_mcb->next = next;
    prev->next = new_mcb;
    if(next != 0)
        next->prev = new_mcb;
}

void remove(MCB* mcb)
{
   MCB* prev= mcb->prev;
   MCB* next= mcb->next;
   prev->next = next;
   if(next != 0)
       next->prev = prev;
}


void init()
{
   MCB* first = get_first_mcb();
   first->begin = 0;
   first->size = 0;
   first->prev = 0;
   first->next = 0;   
   first->used = true;
   int * count = get_count();
   (*count) = 0;
   fill_data('0');
}

my_ptr my_alloc(int size)
{   
    MCB *mcb_new, *parent, *child; 
    
    int blocks_sum, blocks_end; 
    int free_memory, right_free_block;
    
    mcb_new = parent = child = 0;
    blocks_sum = blocks_end = 0;
    
    int* mcb_count = get_count();
    MCB* curr = get_first_mcb();
    MCB* next = curr->next;
        
    int left, right;
    bool find_space = false;
    bool find_mcb = false;
    // ищем свободное место между блоками памяти
    // и свободные существующие MCB
    while(next!= 0)
    {
        if(next->used == false)
        {
            if( !find_mcb )
            {
                find_mcb = true;
                mcb_new = next;
            }
            
            next = next->next;
            continue;
        }
        
        left = curr->begin+curr->size;
        right = next->begin;
        if (!find_space && right-left >= size)
        {
            find_space = true;
            parent = curr;
            child = next;
        }
        
        curr = next;
        next = next->next;
        
        blocks_sum += curr->size; //попутно считаем сволько всего занято памяти
        blocks_end = curr->begin + curr->size; 
    }
    
    
    free_memory = MEMSIZE - blocks_sum - (*mcb_count)*sizeof(MCB);
    right_free_block = MEMSIZE - (*mcb_count)*sizeof(MCB) - blocks_end;
    int need_memory = 0;
    
    if(!find_mcb)
        need_memory+= sizeof(MCB);
    if(!find_space)
        need_memory+= size;
    // если недостаточно места возвращаем нулевой указатель.
    if(need_memory > free_memory)
        return my_ptr(0,0);
    //если не нашли свободный МСВ создаем новый
    if(!find_mcb)
    {
        if(right_free_block < sizeof(MCB) )
        {
            my_defragmentation();
            right_free_block += (blocks_end - blocks_sum);
        }
        *mcb_count += 1;
        mcb_new = (MCB*) (data + MEMSIZE - (*mcb_count)*sizeof(MCB));
        right_free_block -= sizeof(MCB);
    }
    // если не нашли пусто свободное место между блоками вделяем память за последним.
    if(!find_space)
    {
        if(right_free_block < size)
        {
            my_defragmentation();
            right_free_block += (blocks_end - blocks_sum);
        }
        parent = curr;
        child = 0;
    }
    
    insert_mcb(parent, mcb_new, child);
    mcb_new->used = true;
    mcb_new->size = size;
    mcb_new->begin = parent->begin + parent->size;
    
    return my_ptr(data, mcb_new);
}

void my_free(my_ptr& ptr)
{
    MCB* mcb= ptr.mcb;
    MCB* last = (MCB*) (data + MEMSIZE - (*get_count())*sizeof(MCB));
    mcb->used = false;
    if(mcb == last)  // если MCB хранится левее остальных он удаляется.
    {
      remove(mcb);
     *get_count() -= 1;
    }
    return;
}

void my_defragmentation()
{
    MCB* curr = get_first_mcb();
    MCB* next = curr->next;
    int right, left;
    while(next!= 0)
    {
        if(next->used == false)
        {
            next = next->next;
            continue;
        }
        
        left = curr->begin+curr->size;
        right = next->begin;
        
        if(right != left)
        {
            memmove(data+left,data+right, next->size);
            next->begin = left;
        }
        curr = next;
        next = next->next;
    }

}
//--------------------------------------------------

//----MY_PTR----------------------------------------
my_ptr::my_ptr(char* d, MCB* mcb): _data(d), mcb(mcb){};

void* my_ptr::get()
{
    return (void*)(_data+ mcb->begin);
}
//--------------------------------------------------
