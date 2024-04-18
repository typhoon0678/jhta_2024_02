package practice0e;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;

        try {
            Socket socket = new Socket("192.168.10.79", 9988);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String outputMsg = scanner.nextLine();
                bufferedWriter.write(outputMsg + "\n");
                bufferedWriter.flush();

                String result = bufferedReader.readLine();
                System.out.println("계산 결과 : " + result);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
