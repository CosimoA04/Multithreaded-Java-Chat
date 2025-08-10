# Multithreaded Java Chat

A simple console-based chat application built in Java using TCP sockets.  
The server uses multi-threading to handle multiple client connections simultaneously, enabling real-time messaging between users.

## Features
- Multi-threaded server to handle concurrent clients.
- Broadcast messaging — all connected clients receive messages instantly.
- Basic client-server architecture in Java.
- Simple text-based interface for easy testing and learning.

## Technologies
- Java 8+
- TCP sockets (ServerSocket / Socket)
- Multi-threading with Thread and Runnable
- Java I/O (BufferedReader, PrintWriter)

## Project Structure
src/
 ├── ServerChat.java     # Server entry point
 ├── ClientChat.java     # Client entry point
 └── ClientHandler.java  # Handles individual client connections

## How to Run

1. Compile the project:
   javac src/*.java

2. Start the server:
   java -cp src ServerChat

3. Start a client:
   java -cp src ClientChat

   By default, the client connects to localhost on port 8080.  
   To connect to a remote server, change the `host` variable in ClientChat.java.

4. Start chatting:
   - Type your name when prompted.
   - Start sending messages — all connected clients will see them.
   - Type /quit to disconnect.
