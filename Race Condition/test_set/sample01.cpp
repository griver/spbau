#include <pthread.h>
#include <cstdio>

void* behavior_one( void* arg )
{
    int* val = (int*)arg;
    (*val) *= 2;
}
void* behavior_two( void *arg )
{
    int* val = (int*)arg;
    (*val) += 2;
}

int main()
{
    int arg = 42;
    pthread_t one;
    pthread_t two;
    pthread_create( &one, NULL, behavior_one,  &arg );
    pthread_create( &two, NULL, behavior_two,  &arg );
    pthread_join( one, NULL );
    pthread_join( two, NULL );
    printf( "%d\n", arg );
    return 0;
}
