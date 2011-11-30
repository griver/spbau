#include "Scheduler.h"



void read(Scheduler & sc)
{
	//freopen("input.txt", "r", stdin);
	int q1, q2;
	cin>>q1>>q2;
	Task ts;
	while(ts.read())
		sc.addTask(ts, 1);

	sc.addWalker(1, q1);
	sc.addWalker(2, q2);
}


int main()
{
	Scheduler sc;
	read(sc);
	sc.execute();

	return 0;
}
