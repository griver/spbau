CXX = g++
SOURCES += main.cpp Lexer.cpp ConcreteReader.cpp Operations.cpp Parser.cpp
HEADERS += Lexer.h ConcreteReader.h Operations.h Parser.h Tokens.h
NAME = -o parser

all: build clean

build: $(SOURCES) $(HEADERS)
	$(CXX) $(SOURCES) $(NAME)
 
clean:
	rm -f *.o *~

remove: clean
	rm -f parser

run: parser
	echo Parse file sample/01...
	./parser samples/01 > temp/01.result
	echo Parse file sample/02...
	./parser samples/02 > temp/02.result
	echo Parse file sample/03....
	./parser samples/03 > temp/03.result

.PHONY: remove clean run


