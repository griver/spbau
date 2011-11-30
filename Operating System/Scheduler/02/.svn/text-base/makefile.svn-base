prog: compile
	g++ -o prog *.o

compile: main.cpp Scheduler.cpp Task.cpp Walker.cpp
	g++ -c *.cpp


clean: 
	rm -f *.o

remove: clean 
	rm -f prog

.PHONY: compile clean remove


    
