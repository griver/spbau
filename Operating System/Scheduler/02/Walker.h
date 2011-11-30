#ifndef TASK_QUEUE
#define TASK_QUEUE

#include <map>
#include "Task.h"


class Walker
{
public:
	typedef vector<pair<int , Task> >::iterator Task_Iterator;

	int quant;
	Task_Iterator last;
	int queue_id;
	vector <pair<int,  Task> >& tasks;
	

public: 
	Walker(int quant, int queue_id,  vector <pair<int,  Task> >& tasks );
	//Walker();
	Task_Iterator getNext();
	Task_Iterator goAround();
	bool inQueue(Task_Iterator& iter);
	Walker& operator = (Walker & a);
	void swap(Walker & a);

	
};


#endif