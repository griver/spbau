#ifndef SCHEDULER_H_Q2
#define SCHEDULER_H_Q2

#include "Walker.h"


class Scheduler
{
public:
	typedef  vector<pair<int , Task> >::iterator Task_Iterator;

				
	vector<pair<int,  Task> > tasks;	//задания или процессы мульти мэп чтобы сопостовлять разным очередям
	vector < Walker > walkers;		
	int time;						//текущее время выполнения


public:
	//наполнение
	void addWalker(int queue_id, int quant);
	void addTask(Task task , int queue_id);

public:
	Scheduler();
	bool update();
	void execute();
};



#endif