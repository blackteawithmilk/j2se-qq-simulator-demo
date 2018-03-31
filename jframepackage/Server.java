package jframepackage;

import java.net.*;
import java.io.*;

/**
 * Server
 */
public class Server {

    Socket socket = null;

    public static void main(String[] args) {
        new Server().launchServer();
    }

    public void launchServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                socket = serverSocket.accept();
                System.out.println(this.receiveMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            DataInputStream dStream = new DataInputStream(socket.getInputStream());
            String str = dStream.readUTF();
            dStream.close();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}