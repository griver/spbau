#include "Scheduler.h"

//-------- Scheduler ----------------------------
Scheduler::Scheduler()
{
	this->tasks.clear();
	this->time = 0;
	walkers.clear();
}


void Scheduler::addWalker(int queue_id, int quant)
{
	walkers.push_back(Walker(quant, queue_id, tasks));
}

void Scheduler::addTask(Task task , int queue_index)
{
	tasks.push_back(pair<int , Task>(queue_index, task) );
}


bool Scheduler::update()
{
	int overed = 0;
	for(Task_Iterator iter = tasks.begin(); iter!=tasks.end(); ++iter)
	{
		Task& ts = iter->second;
		ts.checkState( time );
		if(ts.state == OVER)
			overed++;
	}

	if(overed == tasks.size())
		return false;
	return true;
}

void Scheduler::execute()
{

	vector<pair<int, Task> >::iterator next;
	vector<pair<int, Task> >::iterator prev = tasks.end();
	vector< Walker >::iterator b_walker = walkers.begin();
	vector< Walker >::iterator a_walker = b_walker++;

	while(update())
	{
		int plus_time;
		if( (next = a_walker->getNext()) != tasks.end())
		{
			Task& ts = next->second;
			cout<<time<<" "<<ts.name<<"\n";

			plus_time = ts.runing(a_walker->quant, time);

			if(plus_time == a_walker->quant)
				next->first = b_walker->queue_id;
		}
		else if( (next = b_walker->getNext()) != tasks.end())
		{
			Task& ts = next->second;
			cout<<time<<" "<<ts.name<<"\n";

			plus_time = ts.runing(b_walker->quant, time);
			// if(plus_time < a_walker->quant)				// по условию не понятно должны ли задачи возвращаться из второй очереди    
			//	   	next->first = a_walker->queue_id;						//
		} else
		{
			if(next!= prev || time == 0)
				cout<<time<<" IDLE\n";

			plus_time = 1;									// передпологается что когда процессор не занят он не пропускает целый квант.
			prev = next;
		}

		time += plus_time;
	}
}


// -----------------------------------------------