package practice0e;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketTest {

    public static void main(String[] args) {
        ServerSocket listener;
        Socket socket;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Scanner scanner = new Scanner(System.in);

        try {
            listener = new ServerSocket(9988);
            System.out.println("서버 소켓입니다. 클라이언트의 연결을 기다리고 있습니다.");

            socket = listener.accept();
            System.out.println("클라이언트 소켓 연결됨" + socket.getRemoteSocketAddress());

            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );

            while (true) {
                String inputMsg = bufferedReader.readLine();
                System.out.println("소켓에서 온 Message : " + inputMsg);

                String outputMsg = scanner.nextLine();
                bufferedWriter.write(outputMsg + "\n");
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
