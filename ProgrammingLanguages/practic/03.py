#!usr/bin/env python
#python3

rules = {}

count = int(input("Введите, число правил: "))
for i in range (5):
	string = input("Rule "+str(i)+": " )
	array = string.split(' ')
	rules[array[0]] = array[1]

	
