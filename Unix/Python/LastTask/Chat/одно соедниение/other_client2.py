#!usr/bin/env python

import collections
import pickle
import socket
import struct
import sys
import curses as cs
import time as tm
import traceback
import threading as th
import termios, tty, select, sys

#дохрена импорта надо бы почистить.

#--- Client -------------------
class ChatClient(object):
    def __init__(self, address):
        self.address = address
        self.name = ""
        self.N = 0
        self.stop = False
        self.lock = th.Lock()
        self.last_message = ""
        
    def __enter__(self):
        return self.connect(self)

    def connect(self):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.connect(self.address)
        return self.sock
    #-- Центр --------
    def say_hello(self):
        self.connect()
        ok, self.N = self.request("HELLO", self.name)
        return ok

    def say_bye(self):
        self.request("BYE", self.name) 

    def send_message(self, text): 
        self.last_message = self.name+": "+text
        self.request("SEND", self.last_message )

    def get_message(self):
        ok, self.N, messages = self.request("GET", self.name, self.N)
        if(self.last_message in messages):
            messages.remove(self.last_message)
        if(ok == False): self.stop = True
        return ok, messages
                
    def get_users(self):
        return self.request("GET_USERS")

    def request(self, *items, wait_for_reply=True):
        SizeStruct = struct.Struct("!I")
        data = pickle.dumps(items, 3)
        try:
            self.sock.sendall(SizeStruct.pack(len(data)))
            self.sock.sendall(data)
            if not wait_for_reply:
                return
                
            size_data = self.sock.recv(SizeStruct.size)
            size = SizeStruct.unpack(size_data)[0]
            result = bytearray()
            while True:
                data = self.sock.recv(4000)
                if not data:
                    break
                result.extend(data)
                if len(result) >= size:
                    break
            return pickle.loads(result)
        except socket.error as err:
            print("{0}: может сервер не запущен?".format(err))
            self.sock.close()
            sys.exit(1)


    #-- /Центр ------
    def __exit__(self, *ignore):
        self.sock.close()

    def __del__(self):
        self.sock.close()

#--- /Client ----------------------------------------------------------------------------

def print_users(Users):
    for i in range(len(Users)):
        print(Users[i])

#--- Секция ввода вывода ----------------------------------------------------------------

def isData():
    return select.select([sys.stdin], [], [], 0) == ([sys.stdin], [], [])

def on():
    tty.setcbreak(sys.stdin.fileno())
    
def off(old_settings):
    termios.tcsetattr(sys.stdin, termios.TCSADRAIN, old_settings)

#--- /Секция ввода вывода ---------------------------------------------------------------

def print_chat(chat_client):
    while (chat_client.stop == False):
        chat_client.lock.acquire()
        ok, messages = chat_client.get_message()
        if ok == False:
            print("Сервер не отвечает")
        else:
            for i in range(len(messages)):
                print(messages[i])
        chat_client.lock.release()
        tm.sleep(0.1)    

def main():

    Address = ["localhost", 11011]
    if len(sys.argv) > 1:
        Address[0] = sys.argv[1]
    chat_client = ChatClient(tuple(Address))

    menu = ("Нажмите h(англ) для того, чтобы войти, q- чтобы покинуть этот чат")
    print(menu)
    some = str(input(">> "))
    while(some != "h"):
        if(some == "q"): return
        else: print("У вас не вышло. Попробуйте еще раз.")
        some = str(input(">> "))
    
    while len(chat_client.name) == 0:
        chat_client.name = str(input("Ведите ник: "))

    if not chat_client.say_hello() :
        print("Сервер сказал не надо")
        return
            
    arguments = []
    arguments.append(chat_client)
    thr = th.Thread(target = print_chat, args = arguments )

    old_settings = termios.tcgetattr(sys.stdin)
    try:
        on()
        thr.start()  
        while chat_client.stop == False:
            if isData():
                chat_client.lock.acquire()
                c = sys.stdin.read(1)
                if c == "m" or c == "ь":
                    off(old_settings)
                    text = str(input(chat_client.name+": " ))
                    on()
                    chat_client.send_message(text)
                elif c == "b" or c == "и":
                    chat_client.say_bye()
                    chat_client.stop = True
                elif c == "g":
                    print_users(chat_client.get_users())
                chat_client.lock.release()
    finally:
        off(old_settings)

main()









