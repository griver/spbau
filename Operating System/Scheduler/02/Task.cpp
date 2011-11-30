#include "Task.h"

//#include <>



//---------- IO ---------------------------------------
IO::IO()
{
		t = 0;
		l = 0;
}

IO::IO(int _t, int _l)
{
		t= _t;
		l = _l;
}

//-----------------------------------------


//------- Task -------------------------------------
Task::Task()
{
	blocks.clear();
	stage = -1;
	io_start_time = -1;
	T = -1;
	L = 0;
	state = NOT_STARTED;
	name = "";
}

bool Task::read()
{
		stage = 0;
		blocks.clear();
		cin>>name;
		cin>>T;
		cin>>L;
		char c;
		int t, l;
		IO cur;
		if(cin.eof())
			return false;

		while(cin.get(c) && c!='\n')
		{
			if(c=='(')
			{
				cin>>t>>c>>l>>c;
				blocks.push_back(IO(t, l));
			}
		}
		return true;
}

void Task::checkState(int _T)
{

	if(state == NOT_STARTED)
	{
		if(T<=_T)
			state = RUNNING;
	}
	
	if(state == RUNNING)
	{
		if(stage == L)
		{
			state = OVER;
		}
		else if( blocks.size() && stage == blocks[0].t)
		{
			state = WAITING_IO;
			io_start_time = _T;
		}
	}

	if(state == WAITING_IO)
		if((_T-io_start_time)>=blocks[0].l)
		{
			stage = blocks[0].t+blocks[0].l;
			state = RUNNING;
			blocks.pop_front();
		}

	if(stage >= L)
		state = OVER;
}

int Task::runing(int _Q, int _T)
{
	int work_time = 0;

	if( blocks.size()!=0)
	{
		if( stage + _Q >= blocks[0].t )
		{
			work_time = blocks[0].t - stage;
			state = WAITING_IO;
			io_start_time = _T + work_time;
		}
		else
			work_time = _Q;
	}
	else
	{
		if(stage + _Q >= L)
		{
			work_time = L - stage;
			state = OVER;
		}
		else
			work_time = _Q;
	}

	stage += work_time; 
	return work_time;
}

int Task::getTime(int _Q)
{
	int work_time = 0;

	if( blocks.size()!=0)
	{
		if( stage + _Q >= blocks[0].t )
			work_time = blocks[0].t - stage;
		else
			work_time = _Q + 1;		// любой процесс не укладувающийся в квант
	}
	else
	{
		if(stage + _Q >= L)
			work_time = L - stage;
		else
			work_time = _Q + 1;   // любой процесс не укладувающийся в квант
	}
	return work_time;
}

//----------------------------------------------------------------
