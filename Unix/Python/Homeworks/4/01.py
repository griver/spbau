#!usr/bin/env python
#python3
import sys
alpha = "\N{GREEK SMALL LETTER ALPHA}"
omega = "\N{GREEK SMALL LETTER OMEGA}"
for letter in range (ord(alpha), ord(omega)+1):
	print(chr(letter), end=' ' )        
print()
	
