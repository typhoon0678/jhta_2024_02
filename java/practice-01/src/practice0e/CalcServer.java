package practice0e;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class CalcServer {

    public static void main(String[] args) {
        ServerSocket listener;
        Socket socket;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;

        try {
            listener = new ServerSocket(9988);
            socket = listener.accept();
            System.out.println("연결");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                String inputMsg = bufferedReader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(inputMsg, " ");
                int num1 = Integer.parseInt(stringTokenizer.nextToken());
                String operator = stringTokenizer.nextToken();
                int num2 = Integer.parseInt(stringTokenizer.nextToken());

                String result;
                switch (operator) {
                    case "+":
                        result = Integer.toString(num1 + num2);
                        break;
                    case "-":
                        result = Integer.toString(num1 - num2);
                        break;
                    default:
                        result = Integer.toString(num1 + num2);
                        break;
                }

                bufferedWriter.write(result + "\r\n");
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
