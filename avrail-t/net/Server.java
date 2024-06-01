package net;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private List<Socket> clients = new ArrayList<>();
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clients.add(clientSocket);
            System.out.println("A new client has joined us");
            new ClientHandler(clientSocket).start();
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public void run() {
            try {
                while (true) {
                    System.out.println("we are waiting to read the line");
                    String input = in.readLine();
                    if (input == null) {
                        break;
                    }

                    // Broadcast message to all clients
                    for (Socket client : clients) {
                        System.out.println("We are in the for loop");
                        System.out.println("Received from client: " + client.getLocalSocketAddress() + "" + input);
                        if (client != socket) {
                            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                            out.println(input);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}