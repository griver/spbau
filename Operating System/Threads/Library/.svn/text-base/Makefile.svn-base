all: prog

prog: main.cpp
	g++ -o prog main.cpp -lpthread -std=c++0x

clean: 
	rm -f *.o

remove: clean 
	rm -f prog

.PHONY: clean remove
