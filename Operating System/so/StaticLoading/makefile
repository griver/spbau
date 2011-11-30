all: prog
	export LD_LIBRARY_PATH=.
prog: main.o libps.so
	gcc main.o -L. -lps -o prog

main.o: main.c
	gcc -c main.c

libps.so: psnapshot.o
	gcc -shared psnapshot.o -o libps.so

psnapshot.o: psnapshot.c
	gcc -fPIC -c psnapshot.c

.PHONY: clean remove 

clean: 
	rm -f *.o *.so*

remove: clean 
	rm -f prog
   
