#include <cstdio>
#include <pthread.h>
#include <vector>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <locale.h>

using namespace std;

pthread_mutex_t* forks;
int* phil_index;
int n; 
int * choise;

int read_number()
{
	setlocale( LC_ALL,"Russian" ); 
//	printf("Введите количество Философов: \n");
	int n = 0;
	scanf("%d", &n);
	return n;
}

unsigned int random_value()
{
        return (rand()%450+51)*1000;
}


void * sample_func(void * arg)
{
	int num =*((int *) arg);
//	printf("Философ номер %d готов\n", num);
	
	while(true)
	{
		choise[num]=rand()%2;

		if(pthread_mutex_trylock(&forks[(num+choise[num])%n])==0 )
		{
			if(choise[num] == 0)
				printf("TAKE_RIGHT_FORK %d\n", num);
			else 
				printf("TAKE_LEFT_FORK %d\n", num);
			choise[num]=(int)(!choise[num]);
			
			if(pthread_mutex_trylock(&forks[(num + choise[num])%n]) == 0 )
			{
				if(choise[num]== 0)
					printf("TAKE_RIGHT_FORK %d\n", num);
				else
					printf("TAKE_LEFT_FORK %d\n", num);
				printf("START_EATING %d\n", num);
				usleep(random_value());

				printf("PUT_RIGHT_FORK %d\n", num);
				pthread_mutex_unlock(&forks[num%n]);

				printf("PUT_LEFT_FORK %d\n", num);
				pthread_mutex_unlock(&forks[(num+1)%n]);
			}
			else
			{
				if(choise[num] == 0)
					printf("PUT_LEFT_FORK %d\n", num);
				else
					printf("PUT_RIGHT_FORK %d\n", num);
				choise[num] =(int)(!choise[num]);
				pthread_mutex_unlock(&forks[(num + choise[num])%n]);
			}
		}

		printf("START_THINKING %d\n", num);
		usleep(random_value());
	}
	return NULL;
}


void create_philosophs(int n)
{
	if(n<2 || n>100)
	{
		printf("Не корректные данные\n");
		return;
	}

	pthread_t* philosoph = new pthread_t[n];
	phil_index = new int[n];
	choise = new int[n];

	for(int i=0; i< n; i++)
	{
		phil_index[i] = i;
		pthread_create(&philosoph[i], NULL, sample_func, &phil_index[i]);
	}
	for(int i=0; i<n; i++)
		pthread_join(philosoph[i], NULL);
}

void create_forks(int n)
{
	forks = new pthread_mutex_t[n];
	for(int i=0; i<n; i++)
		pthread_mutex_init(&forks[i], NULL);
}

int main()
{
	srand( time(0) );
	n = read_number();
	create_forks(n);
	create_philosophs(n);
	return 0;
}
