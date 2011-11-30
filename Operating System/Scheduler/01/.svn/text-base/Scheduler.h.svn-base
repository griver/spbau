#ifndef SCHEDULER_H
#define SCHEDULER_H

#include<iostream>
#include <vector>
#include <deque>

#include "Task.h"

struct Scheduler
{
public:
	int Q;					// квант 
	vector < Task > tasks;	//задания или процессы
	int time;				//текущее время выполнения
	int last;				// переменная для обхода по кругу

public:
	Scheduler();
	int getNext();
	bool update();
	void execute();
	int goAround();
};



#endif