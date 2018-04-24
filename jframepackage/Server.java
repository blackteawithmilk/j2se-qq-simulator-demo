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
    boolean isClientConnected = false;
    DataInputStream dStream = null;

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
                dStream = new DataInputStream(socket.getInputStream());
                isClientConnected = true;
                System.out.println("Client connected...");
                while (isClientConnected) {
                    receiveMessage();
                }
                dStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void receiveMessage() {
        try {
            String str = dStream.readUTF();
            System.out.println("Recv Msg: "+str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}