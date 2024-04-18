package practice0e;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// socket이 전송한 데이터를 실시간으로 서버에 전달하고 서버가 데이터 받아서
// 나머지 애들에게 싹다 보내주기...


// gui
public class MultiChatServer {
    //여러 소켓이 접속할 경우 담아두는 공간
    List<ServerWorker> socketList = new ArrayList<>();
    ServerSocket listener;

    //Socket socket;
    class ServerWorker implements Runnable {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;

        Socket socket;

        public ServerWorker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            //Reader Writer 텍스트 주고 받는 보조 스트림
            //클라이언트에서 이름 부여...
            try {
                bufferedReader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter =
                        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String userName = bufferedReader.readLine();
                broadCasting(userName + "님이 등장 두둥탁 \n");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (true) {
                try {
                    String inputMsg = bufferedReader.readLine();
                    System.out.println("inputMsg===" + inputMsg);
                    //읽고나서 바로 모든 연결된 socket들에게 송출
                    broadCasting(inputMsg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //넘어 들어온 데이터를 읽어서 나머지에게 실시간 전송해주는 코드 작성
            }
        }
    }

    public MultiChatServer() {
        try {
            listener = new ServerSocket(9977);
            while (true) {
                Socket socket = listener.accept();
                ServerWorker serverWorker = new ServerWorker(socket);
                socketList.add(serverWorker);
                Thread thread = new Thread(serverWorker);
                thread.start();
                //실시간 데이터 주고 받기 가능해짐...
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadCasting(String msg) {
        //데이터 송출
        for (int i = 0; i < socketList.size(); i++) {
            ServerWorker serverWorker = socketList.get(i);
            try {
                serverWorker.bufferedWriter.write(msg + "\n");
                serverWorker.bufferedWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //몇명 드러와 있는지  dashboard 관리자가 tel
    }

    public static void main(String[] args) {
        new MultiChatServer();
    }

}