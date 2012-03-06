all: prog

prog: 02.cpp
	g++ 02.cpp  -o prog

run: prog
	./prog
 
remove:	clean
	rm -f prog

clean:
	rm -f *.o
	
.PHONY: clean remove run

