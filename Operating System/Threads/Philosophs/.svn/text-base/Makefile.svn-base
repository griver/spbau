all: prog

prog: main.cpp
	g++ -o prog main.cpp -lpthread

clean: 
	rm -f *.o

remove: clean 
	rm -f prog

.PHONY: clean remove
