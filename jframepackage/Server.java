package jframepackage;

import java.net.*;
import java.io.*;

/**
 * Server
 */
public class Server {

    ServerSocket serverSocket = null;
    Socket socket = null;
    boolean isServerStarted = false;

    public static void main(String[] args) {
        new Server().launchServer();
    }

    public void launchServer() {
        try {
            serverSocket = new ServerSocket(8080);
            isServerStarted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (isServerStarted) {
            try {
                socket = serverSocket.accept();
                System.out.println(this.receiveMessage());
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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