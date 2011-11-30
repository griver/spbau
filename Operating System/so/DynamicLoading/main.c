#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <dirent.h>
#include <stdlib.h>
#include <signal.h>
#include <dlfcn.h>


double (*functionPointer)();

void* openLib(char* sharedObject )
{
	void* soHandle;
	soHandle = dlopen(sharedObject, RTLD_LAZY);
	if(!soHandle )
	{
		printf("Terible error!\n %s\n", dlerror());
		return NULL;
	}
	
	functionPointer = dlsym( soHandle, "execPs");
	if(dlerror()!= NULL)
	{
		printf("Another terible error!\n");
		return NULL;
	}

	return soHandle;
}

void closeLib( void* soHandle )
{
	dlclose(soHandle);
}

void readCommand(char* cmd, char* arg)
{
	printf("Введите команду:\n");
	char* input;
	size_t inputSize;
	

	getline(&input, &inputSize, stdin);

	char *cmdEnd = strchr(input, ' ');
	if(cmdEnd!=NULL)
	{
		*cmdEnd = 0;
		strcpy(cmd, input);
		strcpy(arg, cmdEnd + 1);
	}
	else 
	{
		strcpy(cmd, input);
		arg ="";
	}
	
}

int execCommand(char * cmd, char * arg)
{
	if(strstr(cmd, "ls")!=NULL)
		execDef("ls", arg);
	else if(strstr(cmd, "pwd")!= NULL)
		execDef("pwd", arg);
	else if(strstr(cmd, "ps")!= NULL)
		(*functionPointer)();
	else if(strstr(cmd, "kill")!= NULL)
		execKill("kill", arg);
	else if(strstr(cmd, "cd")!= NULL)
		execCd("cd", arg);
	else if(strstr(cmd, "exit")!=NULL)
		return 1;
	else
		execDef(cmd, arg); 

	return 0;
}

int execDef(char* cmd, char* arg)
{
	
	char buf[256];
	char input[256]={0};
	strcat(input, cmd);
	strcat(input, " ");
	strcat(input, arg);
	
	
	FILE* pipe =popen(input, "r");
	if(pipe == NULL)
	{
		perror("popen");
		exit(1);
	}
	while(fgets(buf, 256, pipe))
		printf("%s\n", buf);
	pclose(pipe);
	return 0;
}

int execKill(char* arg)
{
	return kill(atoi(arg), SIGKILL);
}

int execCd(char* arg)
{
	if(strlen(arg)!= 0)
		 arg = getenv("HOME");
	if(chdir(arg))
	{
		perror("chdir");
		return 1;
	}
	return 0;
	
}

int main()
{
	char cmd[256];
	char arg[256];
	void *psHandle = openLib("./libps.so");
	if(psHandle == NULL )
	{
		printf("Error in shared object loading\n");
		return 0;
	}
	
	do     readCommand(cmd, arg);
	while(!execCommand(cmd, arg));

	closeLib( psHandle );
	return 0;
}

