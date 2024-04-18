package practice0e;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PracticeE {

    public static void main(String[] args) throws InterruptedException {

//        new Practice1Server();
//        Thread.sleep(1000);
//        new Practice1Client();
//
//        new Practice2Server();
//        Thread.sleep(1000);
//        new Practice2Client();
//        new Practice2Client();

        new Practice3Server();
        Thread.sleep(1000);
        new Practice3Client();
        new Practice3Client();
    }
}

class Practice1Server implements Runnable {
    private BufferedWriter bufferedWriter;
    private ServerSocket listener;
    private Socket socket;

    public Practice1Server() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            listener = new ServerSocket(9977);
            socket = listener.accept();
            System.out.println("Connected");

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

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

class Practice1Client implements Runnable {
    private BufferedReader bufferedReader;
    private Socket socket;

    public Practice1Client() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 9977);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                if (bufferedReader.ready()) {
                    String msg = bufferedReader.readLine();
                    System.out.println(msg);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Practice2Server implements Runnable {
    List<Practice2Worker> socketList = new ArrayList<>();
    private ServerSocket listener;

    public Practice2Server() {
        Thread thread = new Thread(this);
        thread.start();
    }

    class Practice2Worker implements Runnable {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Socket socket;

        public Practice2Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                while (true) {
                    String inputMsg = bufferedReader.readLine();

                    String[] calString = inputMsg.split(" ");
                    String outputMsg;

                    try {
                        switch (calString[1]) {
                            case "+":
                                outputMsg = String.valueOf(Integer.parseInt(calString[0]) + Integer.parseInt(calString[2]));
                                break;
                            case "-":
                                outputMsg = String.valueOf(Integer.parseInt(calString[0]) - Integer.parseInt(calString[2]));
                                break;
                            default:
                                outputMsg = "ERROR";
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        outputMsg = "ERROR";
                    }

                    System.out.println("Server : " + inputMsg + " = " + outputMsg);

                    try {
                        bufferedWriter.write(outputMsg + "\n");
                        bufferedWriter.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        try {
            listener = new ServerSocket(9977);
            while (true) {
                Socket socket = listener.accept();
                Practice2Worker serverWorker = new Practice2Worker(socket);
                System.out.println("Connected");
                socketList.add(serverWorker);

                Thread thread = new Thread(serverWorker);
                thread.start();
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
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 9977);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                String calString = scanner.nextLine();
                bufferedWriter.write(calString + "\n");
                bufferedWriter.flush();

                String msg = bufferedReader.readLine();
                System.out.println(id + calString + " = " + msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Practice3Server implements Runnable {
    List<Practice3Worker> socketList = new ArrayList<>();
    private ServerSocket listener;

    public Practice3Server() {
        Thread thread = new Thread(this);
        thread.start();
    }

    class Practice3Worker implements Runnable {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Socket socket;

        public Practice3Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                while (true) {
                    String inputMsg = bufferedReader.readLine();
                    String outputMsg;

                    try {
                        if (Math.random() < 0.5) {
                            outputMsg = "Yes";
                        } else {
                            outputMsg = "No";
                        }
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        outputMsg = "ERROR";
                    }

                    System.out.println("Server : " + inputMsg + " = " + outputMsg);

                    try {
                        bufferedWriter.write(outputMsg + "\n");
                        bufferedWriter.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        try {
            listener = new ServerSocket(9977);
            while (true) {
                Socket socket = listener.accept();
                Practice3Worker serverWorker = new Practice3Worker(socket);
                System.out.println("Connected");
                socketList.add(serverWorker);

                Thread thread = new Thread(serverWorker);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Practice3Client implements Runnable {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);

    private String id = String.format("Client %2d : ", (int) (Math.random() * 100));

    public Practice3Client() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 9977);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                String word = scanner.nextLine();
                bufferedWriter.write(word + "\n");
                bufferedWriter.flush();

                String msg = bufferedReader.readLine();
                System.out.println(id + msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
