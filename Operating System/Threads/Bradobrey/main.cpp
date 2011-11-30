#include <cstdio>
#include <pthread.h>
#include <vector>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <locale.h>
#include <deque>
using namespace std;

int K, T;
double dT;



pthread_mutex_t room_mutex = PTHREAD_MUTEX_INITIALIZER; 
pthread_cond_t  room_cond = PTHREAD_COND_INITIALIZER;
vector <int> room;
deque < int > customers(0);

void init()
{
	scanf("%d", &K );
	scanf("%lf" , &dT);

	dT*= 1000.0; 
	T =(int)dT;
	T *=1000;
	//printf("double Time = %lf\n", dT);
	//printf("int Time = %d\n", T);

	srand(time(0));
	
	room.assign(K, -1); 
}



 
void * bradobrey_behavior(void * arg)
{
	int first, customer;
	while(true)
	{
		if(pthread_mutex_lock( &room_mutex ) == 0)
		{
			customer=-1;

			if(customers.size() == 0)
			{
				printf("START_WAITING_FOR_CUSTOMERS\n");
                                pthread_cond_wait( &room_cond, &room_mutex );
                                printf("WAKED_UP\n");

			}

			first =	customers[0];	
			customer = room[first];
			room[first] = -1;

			printf("START_SHAVING %d\n", customer);

			pthread_mutex_unlock( &room_mutex );
			usleep(T);

			printf("LEAVING_SHAVED %d\n", customer);

			if(pthread_mutex_lock(&room_mutex) == 0)
			{
				customers.pop_front();
				pthread_mutex_unlock(&room_mutex);
			}
									
		}
	}

	return NULL;
}

void customers_appear()
{
	usleep((rand()%451+50)*1000);
}

void * customers_behavior(void * arg)
{
	unsigned int customer = 0;
	int free_chear;
	while(true)
	{
		customers_appear();
		if(pthread_mutex_lock(&room_mutex) == 0)
		{
			printf("NEW_CUSTOMER %d\n", customer);
			free_chear = 0;
			for(int i=0; i<K; i++)
				if(room[i]== -1)
				{
					free_chear= 1;
					room[i]=customer;
					customers.push_back(i);
					break;	
				}			

			if(!free_chear)	
				printf("LEAVING_UNSHAVED %d\n", customer);

			else if(customers.size() == 1)
				pthread_cond_signal( &room_cond );
			else 
				printf("START_WAITING_FOR_SHAVING %d\n", customer);

			pthread_mutex_unlock(&room_mutex);
		}
		customer++;
	}
	
	return NULL;
}

int main()
{

	init();
	pthread_t bradobrey;
	pthread_t world;
	pthread_create(&bradobrey, NULL, bradobrey_behavior,  NULL);
	pthread_create(&world, NULL, customers_behavior,  NULL);
	pthread_join(bradobrey, NULL);
	pthread_join(world, NULL);
	return 0; 
}
