#! /usr/bin/env python

import socket, sys



from SocketServer import ThreadingMixIn , TCPServer , BaseRequestHandler


host = socket.gethostname()
port = 1234


if sys.argv[1] == 'server':
	class MyHandler( BaseRequestHandler ):
		N = 0
		people = {}
		talk = {}
		m = 5

		def handle(self):
		
			#print 'Got conncetion from ' , self.client_address
			while True:
				st = self.request.recv(1024)
				st = st.split(' ', 1)
				length = st[0]
				msg = st[1]
				msg = msg.split(' ', 1)
				#print "msg = ", msg
				if len(msg) == 1:
					if msg[0] == 'bye':
						del MyHandler.people[ self.client_address ]
						break

				else:
					if msg[0] == 'hello':
						if len( MyHandler.people ) < MyHandler.m:
							MyHandler.people[ self.client_address ] = msg[1]  	
							self.request.sendall( 'y ' + str( MyHandler.N ) )
						else:
							self.request.sendall( 'n ' + str( MyHandler.N ) )
							break
						#print MyHandler.people

					if msg[0] == 'send':
						MyHandler.talk[ MyHandler.N ] = MyHandler.people[ self.client_address ] + ': ' + msg[1]
						MyHandler.N += 1
						#print MyHandler.talk
					
					if msg[0] == 'get':
						N_start = int( msg[1] )
						data = str( MyHandler.N-1 )
						for i in range( N_start , MyHandler.N ):
							data += '\n' + MyHandler.talk[i]
						self.request.sendall( data )			

	class MyServer( ThreadingMixIn , TCPServer ):
		allow_reuse_adress = 1	

	server = MyServer( ( host , port ) , MyHandler )
	server.serve_forever()



if sys.argv[1] == 'client':
	
	while True:
			
		st = raw_input( "$: " )    
		st = str(len( st )) + ' ' + st
		
		if st.split(' ')[1] == 'hello':
			LAST_ITERATION_GET = 0
			s = socket.socket()
			s.connect(( host, port ))
			s.sendall( st )
			ans = s.recv(1024)
			if ans.split(' ')[0] == 'no':
				s.close()
			print ans
		
		if st.split(' ')[1] == 'get':
			
			if st.split(' ')[2] == 'N':
				new = st.split(' ')
				new[2] = str( LAST_ITERATION_GET )
				st = ' '.join( new )
					
			s.sendall( st )
			get_talk = s.recv(1024)	
			get_talk = get_talk.split('\n', 1)
			LAST_ITERATION_GET = int( get_talk[0] )
			print get_talk[1]
		
		if st.split(' ')[1] == 'bye':
			s.sendall( st )
			s.close()
			
		
		if st.split(' ')[1] == 'send':
			s.sendall( st )





