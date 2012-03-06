#!usr/bin/ env python
#python3

import contextlib
import copy
import gzip
import os
import pickle
import random
import socketserver
import struct
import sys
import threading
import time as tm

class Finish(Exception): pass

class RequestHandler(socketserver.StreamRequestHandler):

    MessagesLock = threading.Lock()
    UsersLock = threading.Lock()

    Command = dict(
            GET = (lambda self, *args: self.get_messages(*args)),
            SEND = (lambda self, *args: self.send_message(*args)),
            BYE = (lambda self, *args: self.bye_user(*args)),
            HELLO = (lambda self, *args: self.hello_user(*args)),
            GET_USERS = (lambda self: self.get_users()),
            KILL = lambda self, *args: self.shutdown(*args))


    def handle(self):
        self.sad_bye = False
        while not self.sad_bye:
            SizeStruct = struct.Struct("!I")
            size_data = self.rfile.read(SizeStruct.size)
            size = SizeStruct.unpack(size_data)[0]
            data = pickle.loads(self.rfile.read(size))

            try:
            #with self.CallLock:
                function = self.Command[data[0]]
                reply = function(self, *data[1:])
                #if(data[0] == "BYE"): return
            except Finish:
                return
            data = pickle.dumps(reply, 3)
            self.wfile.write(SizeStruct.pack(len(data)))
            self.wfile.write(data)

    def bye_user(self, username):
        self.sad_bye = True
        self.UsersLock.acquire()
        if username in self.Users:
            self.Users.remove(username)
            self.UsersLock.release()
            self.MessagesLock.acquire()
            self.Messages.append("Пользователь "+username+" покинул чат")
            print("Пользователь "+username+" покинул чат")
            self.MessagesLock.release()
            return    
        else: 
            self.UsersLock.release()
            return     

    def hello_user(self, username):
        self.UsersLock.acquire()
        if username not in self.Users:
            self.Users.append(username)
            self.UsersLock.release()
            self.MessagesLock.acquire()
            self.Messages.append("Пользователь "+username+" присоединился к чату")
            print("Пользователь "+username+" присоединился к чату")
            self.MessagesLock.release()
            return True, len(self.Messages)     
        else: 
            self.UsersLock.release()
            return False, len(self.Messages)     
    
    def send_message(self, text):        
        self.MessagesLock.acquire()
        self.Messages.append(text)
        print(text)
        self.MessagesLock.release()

    def get_messages(self, name, N):
        mess = copy.copy(self.Messages[N:])
        new_N = len(self.Messages)
       # print("name:"+str(name)+"  N: "+str(N)+"  new_N: "+str(new_N))
        return True, new_N, mess
        
    def get_users(self):
        return self.Users    

    def shutdown(self, *ignore):
        self.server.shutdown()
        raise Finish()
        

class ChatServer(socketserver.ThreadingMixIn,
                            socketserver.TCPServer): pass


def main():
    messages = []
    users = []

    print("{0} стартанули наш сервер! В нем сейчас {1}".format("Мы", "0 сообщения"))
    RequestHandler.Messages = messages
    RequestHandler.Users = users  
    server = None
    try:
        server = ChatServer(("", 11011), RequestHandler)
        server.serve_forever()
    except Exception as err:
        print("Ошибка", err)
    finally:
        if server is not None:
            server.shutdown()
            print("Cервер убили - {0}. На момент смерти нем хранилось {1} сообщений".format("мы", len(messages)))
            for i in range(len(messages)):
                print(messages[i]) 


main()
