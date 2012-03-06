all: prog

prog: 03.cpp
	g++ 03.cpp  -o prog

run: prog
	./prog
 
remove:	clean
	rm -f prog

clean:
	rm -f *.o
	
.PHONY: clean remove run

