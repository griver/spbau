import collections
import pickle
import socket
import struct
import sys
import Console
import curses as cs
import time as tm
import traceback
import threading as th
import termios, tty, select, sys


Address = ["localhost", 11011]



class SocketManager:

    def __init__(self, address):
        self.address = address


    def __enter__(self):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.connect(self.address)
        return self.sock


    def __exit__(self, *ignore):
        self.sock.close()

#-----Секция ввода вывода-------------------------------------------------------------------

def isData():
    return select.select([sys.stdin], [], [], 0) == ([sys.stdin], [], [])

def on():
    tty.setcbreak(sys.stdin.fileno())
    
def off(old_settings):
    termios.tcsetattr(sys.stdin, termios.TCSADRAIN, old_settings)




#------------------------------------------------------------------------------------------

name = ""
N = 0
stop = False
thread_lock = th.Lock()
last_message = ""

def print_chat():
    global N, name, stop, thread_lock
    while (stop == False):
        thread_lock.acquire()
        ok, new_N, messages = get_message(name, N)
        if ok == False:
            stop = True
            print("Сервер не отвечает")
        else:
            for i in range(len(messages)):
                print(messages[i])
            N = new_N
        thread_lock.release()
        tm.sleep(0.2)    
    

def main():
    global N, name, stop, thread_lock    

    if len(sys.argv) > 1:
        Address[0] = sys.argv[1]
    call = dict(h = say_hello, b = say_bye, m = send_message, g = get_message)
    menu = ("Нажмите h(англ) для того, чтобы войти, q- чтобы покинуть этот чат")
    print(menu)
    some = str(input(">> "))
    while(some != "h"):
        if(some == "q"): return
        else: print("У вас не вышло. Попробуйте еще раз.")
        some = str(input(">> "))
    
    while len(name) == 0:
        name = str(input("Ведите ник: "))
    
    if not say_hello(name) :
        print("Сервер отказывает либо не найден")
        return
            
    old_settings = termios.tcgetattr(sys.stdin)
    try:
        on()
        thr = th.Thread(target = print_chat)
        thr.start()  
        while stop == False:
            if isData():
                thread_lock.acquire()
                c = sys.stdin.read(1)
                if c == "m" or с == "ь":
                    off(old_settings)
                    text = str(input(name+": " ))
                    on()
                    call[c](name, text)
                elif c == "b" or c == "и":
                    call[c](name)
                    stop = True
                thread_lock.release()
    finally:
        off(old_settings)
                
        

def say_hello(name):
    global N
    #print("say_hello()")
    ok, N = handle_request("HELLO", name)
    return ok

def say_bye(name):
    #print("say_bye()")
    handle_request("BYE", name) #, wait_for_reply = False)

def send_message(name, text): 
   # print("send_message: "+text)
    global last_message
    last_message = name+": "+text
    handle_request("SEND", last_message )#wait_for_reply = False)

def get_message(name, N):
    global stop, last_message
    #print("get_message()")
    ok, new_N, messages = handle_request("GET", name, N)
    #print("N: "+str(N)+"  New_N: "+str(new_N)+"\n"+str(ok))
    if(last_message in messages):
        messages.remove(last_message)
    return ok, new_N, messages
                

def handle_request(*items, wait_for_reply=True):
    SizeStruct = struct.Struct("!I")
    data = pickle.dumps(items, 3)

    try:
        with SocketManager(tuple(Address)) as sock:
            sock.sendall(SizeStruct.pack(len(data)))
            sock.sendall(data)
            if not wait_for_reply:
                return
            
            size_data = sock.recv(SizeStruct.size)
            size = SizeStruct.unpack(size_data)[0]
            result = bytearray()
            while True:
                data = sock.recv(4000)
                if not data:
                    break
                result.extend(data)
                if len(result) >= size:
                    break
        return pickle.loads(result)
    except socket.error as err:
        print("{0}: is the server running?".format(err))
        sys.exit(1)


main()
