package demopackage;

import java.io.*;
import java.net.*;

/**
 * Server1
 */
public class Server1 {

    ServerSocket serverSocket = null;
    Socket socket = null;
    boolean isServerStarted = false;
    boolean isClientConnected = false;
    DataInputStream dStream = null;

    public static void main(String[] args) {
        new Server1().launchServer();
    }

    public void launchServer() {
        try {
            serverSocket = new ServerSocket(8080);
            isServerStarted = true;
            while (isServerStarted) {
                socket = serverSocket.accept();
                isClientConnected = true;
                dStream = new DataInputStream(socket.getInputStream());
                while (isClientConnected) {
                    System.out.println("Client connected");
                    receiveMessage();
                }
                dStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {
        try {
            String str = dStream.readUTF();
            System.out.println(str);
        } catch (IOException e) {
            isClientConnected = false;
            e.printStackTrace();
        }
    }
}