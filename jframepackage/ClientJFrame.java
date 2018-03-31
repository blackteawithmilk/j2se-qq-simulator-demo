package jframepackage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

/**
 * ClientJFrame
 */
public class ClientJFrame extends JFrame {

    JTextArea tArea = null;
    JTextField tField = null;

    Socket socket = null;

    public static void main(String[] args) {
        ClientJFrame client = new ClientJFrame();
        client.launchClient();
    }

    public void launchClient() {
        this.setLocation(200, 300);
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tArea = new JTextArea();
        tField = new JTextField();
        this.add(tArea, BorderLayout.NORTH);
        this.add(tField, BorderLayout.SOUTH);
        this.pack();
        tArea.setEditable(false);
        tField.addActionListener(new TfListener());
        this.pack();
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
            DataOutputStream dStream = new DataOutputStream(socket.getOutputStream());
            dStream.writeUTF(str);
            dStream.flush();
            dStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class TfListener implements ActionListener {

        String str = tField.getText().trim();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!str.equals("")) {
                tArea.append(str+"\n");
                sendMessage(str);
            }
            tArea.setCaretPosition(tArea.getText().length());
            tField.setText("");
        }
    }
}