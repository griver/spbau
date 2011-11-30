#include <iostream>
#include <deque>
#include <pthread.h>
#include <stdlib.h>
#include <cstdio>
#include <ctime>
#include <vector>
#include <unistd.h>
using namespace std;


 
struct Person
{
	pthread_cond_t cv;
	pthread_mutex_t m;
	pthread_t thread;
	int number;
	Person(): cv(PTHREAD_COND_INITIALIZER), m(PTHREAD_MUTEX_INITIALIZER) {};
	Person(int num): cv(PTHREAD_COND_INITIALIZER), m(PTHREAD_MUTEX_INITIALIZER), number(num) {};
	void print()
	{
		printf(" number of reader is %d\n", number);
	} 
};

struct Writer : public Person
{
	vector<int> writen_books;
	Writer()
	{
                cv = PTHREAD_COND_INITIALIZER;
                m = PTHREAD_MUTEX_INITIALIZER;
                writen_books.clear();
                number = -1;

	};
	Writer(int num)
	{
		cv = PTHREAD_COND_INITIALIZER;
		m = PTHREAD_MUTEX_INITIALIZER;
		writen_books.clear();
		number = num;
	}
	void print()
	{
		printf("writer number is %d vector size is %d\n", number, writen_books.size());
	}
};


struct Book
{
	deque< Person* > read_q;
	deque< Writer* > edit_q;
	int autor;
	bool free;
	pthread_mutex_t m;
	pthread_cond_t cv;

	Book(int _autor): cv(PTHREAD_COND_INITIALIZER), m(PTHREAD_MUTEX_INITIALIZER), autor(_autor)
	{
		free = true;
		read_q.clear();
		edit_q.clear();
	}
	Book(): cv(PTHREAD_COND_INITIALIZER), m(PTHREAD_MUTEX_INITIALIZER), autor(-1)
	{
		free = false ;
		read_q.clear();
		edit_q.clear();
	}
};


int N = 0;
int W = 0;
int R = 0;
int writen_n = 0;  // количество написанных книг

pthread_mutex_t writing = PTHREAD_MUTEX_INITIALIZER ;  // мьютекс для написания книг

vector < Book >		lib;
vector < Writer >	writers;
vector < Person >	readers;

void init()
{
	//printf("books:\n");
	scanf("%d", &N);
	//printf("writers:\n");
	scanf("%d", &W);
	//printf("readers:\n");
	scanf("%d", &R);

	srand(time(0));
	lib.assign(N, Book());
}


void * reader_behavior(void * arg)
{
	Person* _this = ( Person* )arg;
	bool get;
	Person* waiter = NULL;
	int i;

	pthread_mutex_lock(&_this->m );


	while ( true )
	{
		if(writen_n == 0)
			continue;// если в библиотеке нет книг начинаем с начала.

		i = rand()%writen_n;
                //printf("try take book\n");
		if(pthread_mutex_lock(&lib[i].m) == 0)
		{
                    //printf("reader in mutex lib[i].m\n");
			if(lib[i].free)
			{
				lib[i].free = false;
				get = true;
				printf("START_READ %d\n", i);
			}// если книга свободна забираем
			else
			{
				get = false;
				lib[i].read_q.push_back( _this );
				printf("WAIT_READ %d\n", i);
			}//если книга занята становимся в очередь
                      //  printf("mutex free\n");
			pthread_mutex_unlock(&lib[i].m);
		}// проверяем наличие книги
		
		if(get == false)
		{
			pthread_cond_wait(&_this->cv, &_this->m);
			printf("START_READ %d\n", i);
		}//если взять книгу не удалось засыпаем. нас будят и дают нам книгу.

		usleep((rand()%451+50)*1000);
		
		if(pthread_mutex_lock(&lib[i].m) == 0)
        {
			printf("END_READ %d\n", i); 

			if(lib[i].edit_q.size() != 0)
			{
				waiter = lib[i].edit_q[0];
				lib[i].edit_q.pop_front();
			}//если есть писатель
			else if(lib[i].read_q.size() != 0)
			{
				waiter = lib[i].read_q[0];
				lib[i].read_q.pop_front();
			}//если есть очередь читателей
			else
				lib[i].free = true;	//если нет желающих ставим книгу на полку.

			pthread_mutex_unlock(&lib[i].m);
		}//решаем как распорядиться книгой

		if( waiter != NULL && pthread_mutex_lock(&waiter->m) == 0)
		{
			pthread_cond_signal(&waiter->cv);
			pthread_mutex_unlock(&waiter->m);
		}//будим первого в очереди. книга его.

		waiter = NULL;
	}//основной цикл

}//конец функции

void* writer_behavior(void * arg)
{
	Writer* _this = (Writer*) arg;
	pthread_mutex_lock(&_this->m);  
	bool get = true;
	Person* waiter = NULL;	
	int i;

	while ( true )
	{
		if(pthread_mutex_lock(&writing) == 0)
		{
			if(writen_n < N)
			{
				writen_n; 
				lib[writen_n].autor = _this->number;
				_this->writen_books.push_back(writen_n);
				lib[writen_n].free = true;
				printf("PUBLISH %d %d\n", _this->number, writen_n);
				writen_n++;
			}//если есть место пишем книгу

			pthread_mutex_unlock( &writing );
		}// попытка написать книгу
		
		if(_this->writen_books.size() == 0)
			continue; //если нет книг пытаемся написать снова. 
	
		int j = rand()%_this->writen_books.size();
		i = _this->writen_books[j];

	if(pthread_mutex_lock(&lib[i].m) == 0)
        {
            if(lib[i].free)
            {
                lib[i].free = false;
                get = true;
                printf("START_EDIT %d %d\n", _this->number, i);

            }// если книга свободна забираем
            else
            {
                get = false;
                lib[i].edit_q.push_back( _this );
                printf("WAIT_EDIT %d %d\n", _this->number, i);
            }//если книга занята становимся в очередь
 
            pthread_mutex_unlock(&lib[i].m);
        }// проверяем наличие книги
	

	if(get == false)
        {   
            pthread_cond_wait(&_this->cv, &_this->m);
            printf("START_EDIT %d %d\n", _this->number, i);
        }//если взять книгу не удалось засыпаем. нас будят и дают нам книгу.
	
	
        usleep((rand()%451+50)*1000 ); //взяли книгу отправились спать


	if(pthread_mutex_lock(&lib[i].m) == 0)
        {
            printf("END_EDIT %d\n", i);

            if(lib[i].edit_q.size() != 0)
            {
                waiter = lib[i].edit_q[0];
                lib[i].edit_q.pop_front();
            }//если есть писатель
            else if(lib[i].read_q.size() != 0)
            {
                waiter = lib[i].read_q[0];
                lib[i].read_q.pop_front();
            }//если есть очередь читателей
            else
                lib[i].free = true; //если нет желающих ставим книгу на полку.

            pthread_mutex_unlock(&lib[i].m);
        }//решаем как распорядиться книгой
                
                
        if( waiter != NULL && pthread_mutex_lock(&waiter->m) == 0)
        {
            pthread_cond_signal(&waiter->cv);
            pthread_mutex_unlock(&waiter->m);
        }//будим первого в очереди. книга его.

        waiter = NULL;		
	}// основной цикл
}

void run(int W, int R)
{
	
	
        for(int i=0; i<W; i++)
            writers.push_back(Writer(i));
	for(int i=0; i<W; i++)
            pthread_create(&writers[i].thread, NULL, writer_behavior, &writers[i]);
	 
	for(int i=0; i<R; i++)
            readers.push_back(Person(i));
        for(int i=0; i<R; i++)
		pthread_create(&readers[i].thread, NULL, reader_behavior, &readers[i]);
        
	for(int i=0; i<W; i++)
		pthread_join(writers[i].thread, NULL);
	for(int i=0; i<R; i++)
		pthread_join(readers[i].thread, NULL);

}

int main()
{
	init();
	run(W, R);
       // Writer V(1);
       // writer_behavior(&V);
        return 0;
}

