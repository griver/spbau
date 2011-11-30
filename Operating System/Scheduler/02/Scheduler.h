#ifndef SCHEDULER_H_Q2
#define SCHEDULER_H_Q2

#include "Walker.h"


class Scheduler
{
public:
	typedef  vector<pair<int , Task> >::iterator Task_Iterator;

				
	vector<pair<int,  Task> > tasks;	//������� ��� �������� ������ ��� ����� ������������ ������ ��������
	vector < Walker > walkers;		
	int time;						//������� ����� ����������


public:
	//����������
	void addWalker(int queue_id, int quant);
	void addTask(Task task , int queue_id);

public:
	Scheduler();
	bool update();
	void execute();
};



#endif