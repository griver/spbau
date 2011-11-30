#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <deque>

#include "Scheduler.h"
using namespace std;






void read(Scheduler & sc)
{
	//freopen("input.txt", "r", stdin);
	//freopen("output.txt", "w", stdout);
	cin>>sc.Q;
	while(!cin.eof())
	{
		Task ts;
		ts.read();
		sc.tasks.push_back(ts);
	}
}


int main()
{
	Scheduler sc;
	read(sc);
	sc.execute();
	int i;

	return 0;
}
