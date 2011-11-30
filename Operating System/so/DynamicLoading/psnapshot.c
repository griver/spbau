#include <stdio.h>

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

