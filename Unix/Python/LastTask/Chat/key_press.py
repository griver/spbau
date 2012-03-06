"""import termios, fcntl, sys, os
fd = sys.stdin.fileno()

oldterm = termios.tcgetattr(fd)
newattr = termios.tcgetattr(fd)
newattr[3] = newattr[3] & ~termios.ICANON & ~termios.ECHO
termios.tcsetattr(fd, termios.TCSANOW, newattr)

oldflags = fcntl.fcntl(fd, fcntl.F_GETFL)
fcntl.fcntl(fd, fcntl.F_SETFL, oldflags | os.O_NONBLOCK)

try:
    while 1:
        try:
            c = sys.stdin.read(1)
            #if (repr(c) == 'd'):
            print("Got character", repr(c))
        except IOError: pass
finally:
    termios.tcsetattr(fd, termios.TCSAFLUSH, oldterm)
    fcntl.fcntl(fd, fcntl.F_SETFL, oldflags)

"""
import termios, tty, select, sys

def isData():
    return select.select([sys.stdin], [], [], 0) == ([sys.stdin], [], [])

def on():
    tty.setcbreak(sys.stdin.fileno())
    
def off(old_settings):
    termios.tcsetattr(sys.stdin, termios.TCSADRAIN, old_settings)


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
                print("привет добрый самаритянин")         # x1b is ESC
                break
            elif c == 'r':
                print("Не жми больше r это моя любимая клавиша")
                off(old_settings)
                something = str(input("Ну напиши же что-нибуть:"))
                print("Если, что-то и было это было вот оно: {0}".format(something))
                on()
                
finally:
    termios.tcsetattr(sys.stdin, termios.TCSADRAIN, old_settings)
