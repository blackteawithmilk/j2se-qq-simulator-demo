package jframepackage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

/**
 * Client
 */
public class Client extends JFrame {

    JTextArea tArea = null;
    JTextField tField = null;

    Socket socket = null;
    DataOutputStream dStream;

    public static void main(String[] args) {
        Client client = new Client();
        client.launchClient();
    }

    public void launchClient() {
        this.setLocation(200, 300);
        this.setSize(600, 500);
        this.setTitle("¿Í»§¶Ë");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                this.disconnect();
                System.exit(0);
            }
        });

        tArea = new JTextArea();
        tField = new JTextField();
        this.add(tArea, BorderLayout.NORTH);
        this.add(tField, BorderLayout.SOUTH);
        this.pack();
        tArea.setEditable(false);
        tField.addActionListener(new TfListener());

        this.setVisible(true);
        this.connect();
    }
    
    public void connect() {
        try {
            socket = new Socket("127.0.0.1", 8080);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String str) {
        try {
            dStream = new DataOutputStream(socket.getOutputStream());
            dStream.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        dStream.flush();
        dStream.close();
        socket.close();
    }

    private class TfListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = tField.getText().trim();
            if (!str.equals("")) {
                tArea.append(str+"\n");
                sendMessage(str);
            }
            tArea.setCaretPosition(tArea.getText().length());
            tField.setText("");
        }
    }
}