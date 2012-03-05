#!usr/bin/env python
#python3

import copy

def print_exp_n(n):
    print("int exp_"+str(n)+"(int x) {")
    print("    int y = 1;")
    while n != 0:
        if (n % 2):
            print("    y *= x;")
        n = n // 2
        if( n!= 0):
            print("    x *= x;")
    print("    return y;")
    print("}")

n = int(input("Введите N: "))

print_exp_n(n)

