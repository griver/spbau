#!usr/bin/env python
#python3

tmp =int(input("Ввиедите число: "))
val = str(tmp)
rval = val[::-1]

for sym in rval:
	if(sym != '0'):
		print(sym, end='', sep = '')
