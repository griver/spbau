#!usr/bin/env python
#python3

class Car(object):
	def __init__(self):
		print("Создали машинку (:")
	def who(self):
		print("Привет я машинко")
	def __del__(self):
		print("Уничтожили машинку ):") 

class Bulldozer(Car):
	def __init__(self):
		print("Создали бульдозер <-:[")
	def who(self):
		print("А я бульдозер")
	def __del__(self):
		print("Стёрли бульдозер в порошок D-:[")

class DriftCar(Car):
	def __init__(self):
		print("Создали спорткар (-8")
	def who(self):
		print("Я супер машинка")
	def __del__(self):
		print("Разбили спорткар D=")


car = Car()
car.who()
del car
print("-------------------------------")
car = Bulldozer()
car.who()
del car
print("-------------------------------")
car = DriftCar()
car.who()
del car
