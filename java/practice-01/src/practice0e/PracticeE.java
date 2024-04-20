package practice0e;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PracticeE {

    public static void main(String[] args) throws InterruptedException {

//        new Problem1Server();
//        Thread.sleep(1000);
//        new Problem1Client();

//        new Problem2Server();
//        Thread.sleep(1000);
//        new Problem2Client();
//        new Problem2Client();

        new Problem3Server();
        Thread.sleep(1000);
        new Problem3Client();
        new Problem3Client();

    }
}

class Problem1Server implements Runnable {

    public Problem1Server() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket listener = new ServerSocket(9977);
            Socket socket = listener.accept();
            System.out.println("Connected");

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

            int i = 1;
            while (i <= 20) {
                Thread.sleep(500);

                bufferedWriter.write(i++ + "\n");
                bufferedWriter.flush();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class Problem1Client implements Runnable {

    public Problem1Client() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 9977);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                if (bufferedReader.ready()) {
                    String outputMsg = bufferedReader.readLine();

                    System.out.println(outputMsg);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Problem2Server implements Runnable {

    List<Problem2Worker> socketList = new ArrayList<>();
    ServerSocket listener;

    public Problem2Server() {
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        try {
            listener = new ServerSocket(9977);

            while (true) {
                Socket socket = listener.accept();
                Problem2Worker worker = new Problem2Worker(socket);
                socketList.add(worker);
                System.out.println("Connected");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Problem2Worker implements Runnable {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Socket socket;

        public Problem2Worker(Socket socket) {
            this.socket = socket;

            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                while (true) {
                    String inputMsg = bufferedReader.readLine();
                    String[] calcString = inputMsg.split(" ");

                    String outputMsg = switch (calcString[1]) {
                        case "+" -> String.valueOf(Integer.parseInt(calcString[0]) + Integer.parseInt(calcString[2]));
                        case "-" -> String.valueOf(Integer.parseInt(calcString[0]) - Integer.parseInt(calcString[2]));
                        default -> "ERROR";
                    };

                    System.out.println("Server : " + inputMsg + " = " + outputMsg);
                    bufferedWriter.write(outputMsg + "\n");
                    bufferedWriter.flush();

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

class Practice2Client implements Runnable {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);

    private String id = String.format("Client %2d : ", (int) (Math.random() * 100));

    public Practice2Client() {
=======
}

class Problem2Client implements Runnable {

    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private final Scanner scanner = new Scanner(System.in);

    private final String id = String.format("Client %2d : ", (int) (Math.random() * 100));

    public Problem2Client() {

        try {
            Socket socket = new Socket("localhost", 9977);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        while (true) {
            String outputMsg = scanner.nextLine();
            try {
                bufferedWriter.write(outputMsg + "\n");
                bufferedWriter.flush();

                String inputMsg = bufferedReader.readLine();
                System.out.println(id + outputMsg + " = " + inputMsg);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

class Problem3Server implements Runnable {

    List<Problem3Worker> socketList = new ArrayList<Problem3Worker>();
    ServerSocket listener;

    private final List<String> words = new ArrayList<>();

    public Problem3Server() {
        try {
            FileReader fileReader = new FileReader("src/practice0e/words.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader, 16 * 1024);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                words.add(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            listener = new ServerSocket(9977);

            while (true) {
                Socket socket = listener.accept();
                Problem3Worker worker = new Problem3Worker(socket);
                socketList.add(worker);
                System.out.println("Connected");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Problem3Worker implements Runnable {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Socket socket;

        public Problem3Worker(Socket socket) {
            this.socket = socket;

            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                while (true) {
                    String inputMsg = bufferedReader.readLine();

                    String outputMsg = (words.contains(inputMsg)) ? "Yes" : "No";

                    bufferedWriter.write(outputMsg + "\n");
                    bufferedWriter.flush();
                    System.out.println("Server : " + inputMsg + " = " + outputMsg);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class Problem3Client implements Runnable {

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    Socket socket;
    Scanner scanner = new Scanner(System.in);

    private final String id = String.format("Client %2d : ", (int) (Math.random() * 100));

    public Problem3Client() {

        try {
            socket = new Socket("localhost", 9977);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        while (true) {
            String outputMsg = scanner.nextLine();
            try {
                bufferedWriter.write(outputMsg + "\n");
                bufferedWriter.flush();

                String inputMsg = bufferedReader.readLine();
                System.out.println(id + outputMsg + " = " + inputMsg);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
