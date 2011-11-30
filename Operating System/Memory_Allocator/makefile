all: prog

prog: main.o memory_allocator.o
	g++ main.o memory_allocator.o  -o prog
memory_allocator.o: memory_allocator.cpp
	g++ memory_allocator.cpp -c
main.o: main.cpp
	g++ main.cpp -c


clean: 
	rm -rf *.o
 
remove:	clean
	rm -f prog

other:
	g++ main.cpp  -c
	g++ main.o -o prog
	
.PHONY: clean remove other

