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

    public static void main(String[] args) {
        new Server().launchServer();
    }

    public void launchServer() {
        try {
            serverSocket = new ServerSocket(8080);
            isServerStarted = true;
            while (true) {
                socket = serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}