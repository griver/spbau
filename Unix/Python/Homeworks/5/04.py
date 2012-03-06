#!usr/bin/env python
#python3

import sys

n = int(input("Введите чнатуральное число:"))

while(n%2 == 0):
    n/=2
if n == 1:
    print("YES")
else:
    print("NO")
    
