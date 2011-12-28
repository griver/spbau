all: read.out

read.out: read.cpp 
	g++ read.cpp -o read.out
	rm -f read.o 

remove: clean
	rm -f *.out

clean:
	rm -f *~ *.o a.out

test: log.txt read.out
	rm -f *tu
	g++ ../sample01/sample01.cpp -fdump-translation-unit -O0
	./read.out sample01.cpp.001t.tu -t >log.txt	

.PHONY: clean remove test	
