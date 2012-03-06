#!/usr/bin/env python

import socket
from time import time, ctime

IP = ''
PORT = 23456
ADS = (IP, PORT)

tcpsoc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcpsoc.bind(ADS)
tcpsoc.listen(5)

while True:
    print("Waiting for connection")
    tcpcli, addr = tcpsoc.accept()
    print("connected from:", addr)
    while True:
        data = tcpcli.recv(1024)
        if not data : break
        print(data)
        data1 = input(">>")
        if data1 == "quit": break
        tcpcli.send(data1.encode())
    tcpcli.close()
tcpsoc.close()


