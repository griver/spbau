#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <dirent.h>
#include <stdlib.h>
#include <signal.h>

#include "psnapshot.h"

int execPs( void )
{
        char* input ="ps a";
        char buf[256];
        FILE* pipe =popen(input, "r");
        if(pipe == NULL)
        {
                perror("popen");
                return 1;
        }
        while(fgets(buf, 256, pipe))
                printf("%s\n", buf);
        pclose(pipe);
        return 0;
}

