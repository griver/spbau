all: prog

prog: main.o
	gcc -o prog main.o
main.o: main.c
	gcc -c -Wall main.c

.PHONY: clean

clean: 
	rm -f main.o prog
