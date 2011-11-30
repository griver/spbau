#ifndef TASK_H
#define TASK_H

#include <string>
#include <vector>
#include <queue>
#include <iostream>
#include <cstdio>

using namespace std;


enum Task_State
{
	NOT_STARTED = 0, 
	WAITING_IO = 1,
	RUNNING = 2,
	OVER =3
};

struct IO
{
	//конструкторы
	IO();
	IO(int _t, int _l );

	//методы
	int t;
	int l;
};

struct Task
{

	string name;
	unsigned int T;
	unsigned int L;
	unsigned int stage;				//текущая стадия выпролнения. только в блоке выполнения
	unsigned int io_start_time;		//время старта блока ввода вывода
	Task_State state;
	deque< IO > blocks;
	
	Task();
	bool read();
	//bool isOver();
	int	 getTime(int _Q);
	int runing(int _Q, int _T);

	void checkState(int _T);
};




#endif
