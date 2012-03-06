#!usr/bin/env python
#python3

import sys

class Student(object):
	def __init__(self, name):
		self.name = name
		self.grades = [] 

	def append(self, val):
		self.grades.append(int(val))

	def add(self, val):
		self.grades += list(val)

	def mean(self):
		return sum(self.grades)/len(self.grades)

	def __str__(self):
		string = self.name+": " 
		for grade in self.grades:
			string += str(grade)+" "
		return string


class DataBase(object):
	def __init__(self, filename):
		self.__base_file = open(filename)
		self.students = list()
		
	def read_input(self):
		lines = self.__base_file.readlines()
		count = int(lines[0])
		for line in lines[1:count+1]:
			tmp_list = line.split(':')
			stud = Student(tmp_list[0])
			if(len(tmp_list) > 1):
				stud.add([int(grade) for grade in tmp_list[1].split()])
			self.students.append(stud)
	
	def best_students(self):
		means = [stud.mean() for stud in self.students]
		max_mean = max(means)
		for stud in self.students:
			if stud.mean() == max_mean :
				yield stud

	def append(self, student):
		self.students.append(student)
	
	def add(self, students):
		self.students += students
	
	def remove(self, student):
		self.students.remove(student)	

	def __str__(self):
		string = ""
		for student in self.students:
			string += str(student)+"\n"
		return string

	def __del__(self):
		self.__base_file.close() 
			
			
 

filename = input("Введите имя файла: ")		
db = DataBase(filename)
db.read_input()

st =Student("Мария Козлова")
st.add([4,5, 4, 5])
db.append(st)

st = Student("Мистер Шляпа")
st.add([4,4, 5, 5])
db.append(st)

print("\nСписок Успеваемости:")
print(db)

print("Лучшие студенты:")
for i in db.best_students():
	print(i)
	
db.remove(st)

print("\nСписок Успеваемости:")
print(db)
