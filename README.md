Simple-Network-Chat-Client
==========================

CS380 Project 2

In this project, you will be implementing a very simple chat client. I will have a server running that you
can connect to to test your client. The server will work as follows: the first line you send will be stored by
the server as your user name. If it is already in use, the server will respond “Name in use.” and close the
connection.
After connecting and providing a name, any line you send to the server will be broadcast to all connected
clients with a timestamp and the sender’s username
