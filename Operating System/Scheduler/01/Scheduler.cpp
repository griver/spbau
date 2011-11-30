#include "Scheduler.h"

//-------- Scheduler ----------------------------
Scheduler::Scheduler()
{
	this->Q = 1;
	this->tasks.clear();
	this->time = 0;
	this->last = 0;
}





int Scheduler::getNext()
{
	int min_time = Q;
	int index = -1;
	int task_time = -1;
	for(int i=0; i<tasks.size(); i++)
	{
		if(tasks[i].state == RUNNING)
		{
			task_time  = tasks[i].getTime( Q );
			if(min_time >= task_time)
			{
				index =i;
				min_time = task_time;
			}
		}
	}
	if( task_time != -1 &&  index == -1 )
		index = goAround();
	return index;
}

bool Scheduler::update()
{
	vector<Task>::iterator iter = tasks.begin();
	int overed = 0;
	while(iter!=tasks.end())
	{
		iter->checkState( time );
		if(iter->state == OVER)
			overed++;
		iter++;
	}
	if(overed == tasks.size())
		return false;
	return true;
}

void Scheduler::execute()
{
	int next; 
	int	prev = -2;
	time = 0;
	while(update())
	{
		next = getNext();
		if(next < 0)
		{
			if(next != prev)
				cout<<time<<" IDLE\n";   // исправить prev = -1
			time++;						 // передпологается что когда процессор не занят он не пропускает целый квант.
			prev = next;
			continue;
		}
		else
		{
			//if(next != prev)
			cout<<time<<" "<<tasks[next].name<<"\n";
			time += tasks[next].runing( Q, time);
			prev = next;
		}
	}
}


int Scheduler::goAround() {
	do {
		 last = (last+1)%tasks.size();
	}while ( tasks[last].state != RUNNING );
	return last;

}

// -----------------------------------------------