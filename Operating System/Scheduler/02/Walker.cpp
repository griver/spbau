#include "Walker.h"



Walker::Walker(int quant, int queue_id,  vector <pair<int,  Task> >& tasks ): quant(quant), queue_id(queue_id), tasks(tasks)
{
	last = tasks.begin();
};


//Walker::Walker(): quant(0), queue_id(0), tasks(vector <pair<int,  Task> >())
//{
//	
//};

//
Walker& Walker::operator= (Walker & a)
{
	Walker tmp(a);
	swap(tmp);
	return *this;
}




void Walker::swap(Walker& value)
{
		std::swap(last, value.last);
		std::swap(tasks, value.tasks);
		std::swap(queue_id, value.queue_id);
		std::swap(quant, value.quant);
}

vector<pair<int, Task> >::iterator Walker::getNext()
{

	int min_time = quant;
	Task_Iterator index = tasks.end(); 
	int task_time = -1;
	for(Task_Iterator iter = tasks.begin(); iter!= tasks.end() ; ++iter)
	{
		Task ts = iter->second;   //*
		if(inQueue(iter) && ts.state == RUNNING)
		{
			task_time  = ts.getTime( quant );
			if(min_time >= task_time)
			{
				index = iter;
				min_time = task_time;
			}
		}
	}

	
	if( task_time != -1 &&  index == tasks.end() )
		index = goAround();
	return index;
}


vector<pair<int, Task > >::iterator Walker::goAround() 
{
	Task_Iterator next = last;
	next++;
	for(next; next != tasks.end(); next++)
		if(inQueue(next) &&  (*next).second.state == RUNNING)
		{
			last = next;
			return next;
		}

	for(next = tasks.begin(); next <= last; next++)
		if( inQueue(next) && (*next).second.state == RUNNING)
		{
			last = next;
			return next;
		}

		return tasks.end();
}

bool Walker::inQueue(Task_Iterator& iter)
{
	return iter->first == queue_id ;
}