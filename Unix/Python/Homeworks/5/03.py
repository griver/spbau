#!usr/bin/env python
#python3

import re

def print_symbols(n, d):
	while n > 0:
		for key,val in d.items():
			if val == n:
				print( key, ":", val, end='  ', sep = '')
		n -= 1


input_f = str(input("Ввиедите имя файла:")) 
f = open(input_f)
lines = f.read().split("\n")


symbols = re.compile("[a-zA-Z]")
for line in lines:
	ferq = {}
	for sym in symbols.findall(line):
		if sym in ferq:
			ferq[sym] += 1
		else:
			ferq[sym] = 1
	print_symbols(len(line), ferq)
	print("\n-----------")
