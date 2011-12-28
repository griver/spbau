create: gimple generic clean
	
gimple:
	rm -f *t.gimple
	g++ sample01.cpp -fdump-tree-gimple -O0

generic:
	rm -f *t.tu
	g++ sample01.cpp -fdump-translation-unit -O0

clean: 
	rm -f a.out *.o *~

.PHONY: generic gimple create clean
