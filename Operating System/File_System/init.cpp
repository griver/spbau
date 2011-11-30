#include <iostream>
#include <fstream>
#include <string>
#include <sys/stat.h>
#include <sys/types.h>
#include <cstdio>
#include <unistd.h>

using namespace std; 

enum ReturnCode{
	ALL_CORRECT = 0,
	ERROR = 1
};

void init(string root)
{
	int n;
	int block_size;
	string config = root+"/config";
	cout<<config.c_str();
	ifstream f(config.c_str());
	f>>n;
	f>>block_size;
	cout<<"\nn = "<<n<<endl;
	cout<<"block_size = "<<block_size<<endl;
	char str[1024];
	touch(r)
}


int main(int argc, char *argv[] ) {
	ReturnCode code = ALL_CORRECT;
	if(argc == 1)
		code = ERROR;
	else
		init(argv[1]);
	
	return code;
}
