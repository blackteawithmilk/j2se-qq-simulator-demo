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
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tField.addActionListener(new TfListener());
        tArea.setEditable(false);
        tArea.setBackground(Color.WHITE);
        this.setVisible(true);
        connect();
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

    private class TfListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = tField.getText().trim();
            if (!str.equals("")) {
                tArea.append(str+"\n");
            }
            tArea.setCaretPosition(tArea.getText().length());
            tField.setText("");
        }
    }
}