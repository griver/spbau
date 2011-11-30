#ifndef SCHEDULER_H
#define SCHEDULER_H

#include<iostream>
#include <vector>
#include <deque>

#include "Task.h"

struct Scheduler
{
public:
	int Q;					// ����� 
	vector < Task > tasks;	//������� ��� ��������
	int time;				//������� ����� ����������
	int last;				// ���������� ��� ������ �� �����

public:
	Scheduler();
	int getNext();
	bool update();
	void execute();
	int goAround();
};



#endif