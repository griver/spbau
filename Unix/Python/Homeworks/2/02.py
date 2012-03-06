#!usr/bin/env python
#python3

from fractions import gcd

class Aquarius(object):
	def __init__ (self, A, B ):
		self.jag = {"A":0, "B":0}
		self.capasity = {"A": A, "B": B}

	def pourFrom(self, name):
		self.jag[name] = 0
		print(name+">")
		#print("Вылили из кувшина "+name+":")
		#print("   ", self.jag )


	def pourXToY(self, x, y):
		poured = min(self.capasity[y]-self.jag[y], self.jag[x])
		self.jag[y] += poured
		self.jag[x] -= poured
		print(x+">"+y)
		#print("Перелили из кувшина "+x+" в кувшин "+y+":")
		#print("   ", self.jag )


	def fill(self, name):
		self.jag[name] = 0 + self.capasity[name]
		print(">"+name)
		#print("Наплонили кувшин "+name+":")
		#print("   ", self.jag )

	def check(self, val):
		if(self.jag["A"] == val or self.jag["B"] ==val ):
			return True
		else:
			return False

	def isFilled(self, name):
		if(self.jag[name] == self.capasity[name]):
			return True
		else:
			return False
	
	def isEmplty(self, name):
		if(self.jag[name] == 0):
			return True
		else:
			return False
		
def solve(a, b, n):

	Aqua = Aquarius(a,b)	

	if(a >= b):
		maxx = "A"
		minn = "B"
	else:
		maxx = "B"
		minn = "A"

	if( n > max(a,b) ):
		print((">{0:s}\n{0:s}>C\n"*(n // max(a,b)) ).format(maxx), end='' )

	n %= max(a,b)
	if(n == 0):
		return;

	while( Aqua.check(n) == False ):
		if( Aqua.isFilled(minn) ):
				Aqua.pourFrom(minn)
		if( Aqua.isEmplty(maxx) ):
				Aqua.fill(maxx)
		Aqua.pourXToY(maxx, minn)
	
	if(Aqua.jag["A"] == n ):
		print("A>C")
	else:
		print("B>C")


str_input=str(input("Ведите, через проблел, три числа A B N:"))
numbers=str_input.split(' ')
A = int(numbers[0])
B = int(numbers[1])
N = int(numbers[2])

if( N == A ):
	print(">A")
	print("A>C")
elif(N == B):
	print(">B")
	print("B>C")
elif( N % gcd(A,B) != 0):
	print("Impossible")
else:
	solve(A, B, N)
