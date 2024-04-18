package practice0e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChattingServer extends JFrame implements ActionListener {

    private JTextField sender = new JTextField();
    private Receiver receiver = new Receiver();

    public ChattingServer() throws HeadlessException {
        this.setTitle("ChattingServer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        sender.addActionListener(this);

        container.add(new JScrollPane(receiver), BorderLayout.CENTER);
        container.add(sender, BorderLayout.SOUTH);

        Thread thread = new Thread(receiver);
        thread.start();

        this.setSize(400, 200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        receiver.setMsg(sender.getText());
        sender.setText("");
    }

    public static void main(String[] args) {
        new ChattingServer();
    }
}

class Receiver extends JTextArea implements Runnable {

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ServerSocket listener;
    private Socket socket;

    private String msg = "";
    private String id = String.format("ID_%2d", (int) (Math.random() * 100));

    public Receiver() {
        this.setEditable(false);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            // 서버
            listener = new ServerSocket(9977);
            socket = listener.accept();
            this.append("Connected\r\n");

            // 클라이언트
//            socket = new Socket("192.168.10.82", 9977);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (bufferedReader.ready()) {
                    String inputMsg = bufferedReader.readLine();
                    this.append(inputMsg + "\r\n");
                    this.setCaretPosition(this.getDocument().getLength());
                }

                if (!this.msg.isEmpty()) {
                    bufferedWriter.write(id + " : " + this.msg + "\r\n");
                    bufferedWriter.flush();
                    this.append(this.id + " : " + this.msg + "\r\n");
                    this.setCaretPosition(this.getDocument().getLength());
                    this.msg = "";
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}