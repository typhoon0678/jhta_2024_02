package practice0e;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketTest {

    public static void main(String[] args) {
        Socket socket;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Scanner scanner = new Scanner(System.in);

        try {
            socket = new Socket("192.168.10.79", 9988);

            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );

            while (true) {
                String outputMsg = scanner.nextLine();
                bufferedWriter.write(outputMsg + "\n");
                bufferedWriter.flush();

                String inputMsg = bufferedReader.readLine();
                System.out.println("소켓에서 온 Message : " + inputMsg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
