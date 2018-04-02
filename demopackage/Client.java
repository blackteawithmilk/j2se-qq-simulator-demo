package demopackage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * Client
 */
public class Client extends Frame {

    TextArea tArea = null;
    TextField tField = null;

    Socket socket = null;
    DataOutputStream dStream = null;

    public static void main(String[] args) {
        Client client = new Client();
        client.lauchFrame();
    }

    public void lauchFrame() {
        this.setLocation(200, 300);
        this.setSize(700, 600);
        this.setTitle("¿Í»§¶Ë");

        tArea = new TextArea();
        tField = new TextField();
        this.add(tArea, BorderLayout.NORTH);
        this.add(tField, BorderLayout.SOUTH);
        this.pack();
        tArea.setEditable(false);
        tArea.setBackground(Color.WHITE);

        tField.addActionListener(new TfListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnect();
                System.exit(0);
            }
        });
        
        this.setVisible(true);
        connect();
    }

    public void connect() {
        try {
            socket = new Socket("127.0.0.1", 8080);
            dStream = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            dStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
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

        public void sendMessage(String str) {
            if (dStream != null) {
                dStream.writeUTF(str);
                dStream.flush();
            }
        }
    }
}