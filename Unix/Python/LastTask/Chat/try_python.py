#!usr/bin/env python
#python3

import curses as cs
import time as tm
import traceback
import threading as th

def function():
    
        

    List = []

    List.append("Артем")
    List.append("Сорокин")
    #List += data
    print(List)

"""
try: 
    stdscr = cs.initscr()
    cs.noecho()
    cs.cbreak()
    stdscr.keypad(1)
    str_list = ["Привет", "Как дела?", "Что делаешь?", "Ответь!"]
    i = 0
    l = len(str_list)
    j = 0
    j2 =0
    cs.halfdelay(1)
    begin_x = 20 ; begin_y = 7
    height = 5 ; width = 40
    win = cs.newpad(height, width, begin_y, begin_x)
    
    while True:
        c = stdscr.getch()
        if c == ord('x'): 
            stdscr.addstr(j, 0, "ООО!! вы нажали x!!")
            j += 1
        elif c == ord('q'): 
            break
        elif c == ord('z'):
            stdscr.addstr(j, 0, "ООО!! вы нажали ZZZZZ!!")
            j += 1
        win.addstr(j2, 0, str_list[i])
        win.refresh()
        j2 += 1        
        i =  (i+1)%l
        tm.sleep(5)
    print("Все кончено! вы выжили")
    cs.nocbreak(); stdscr.keypad(0); cs.echo()
    cs.endwin()
except:
    cs.nocbreak(); stdscr.keypad(0); cs.echo()
    cs.endwin()
    traceback.print_exc() 



"""
import termios, tty, select, sys

import termios, tty, select, sys

def isData():
    return select.select([sys.stdin], [], [], 0) == ([sys.stdin], [], [])

def on():
    tty.setcbreak(sys.stdin.fileno())
    
def off(old_settings):
    termios.tcsetattr(sys.stdin, termios.TCSADRAIN, old_settings)



x = 0
key = th.Lock()

def func():
    while True:
        key.acquire()
        print("Хреень")
        key.release()
        tm.sleep(0.5)    
        if x == 1: break 


thr = th.Thread(target =func)
print("HEllo World")
thr.start()


old_settings = termios.tcgetattr(sys.stdin)
try:
    tty.setcbreak(sys.stdin.fileno())
    i = 0
    while 1:
     #  /print(i)
        if isData():
            c = sys.stdin.read(1)
            print(c)
            if c == '\x1b':
                print("привет добрый самаритянин")  
                break
            elif c == 'r':
                key.acquire()
                off(old_settings)
                something = str(input("Ну напиши же что-нибуть:"))
                if(something == "stop"):
                    x = 1                    
                on()
                key.release()
                
finally:
    termios.tcsetattr(sys.stdin, termios.TCSADRAIN, old_settings)


def heardEnter(): 
    i,o,e = select.select([sys.stdin],[],[],1)
    for s in i:
        if s == sys.stdin:
            input = sys.stdin.readline()
            return True
    return False
