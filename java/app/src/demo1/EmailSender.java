package demo1;

/*
  이메일로 메세지를 보내는 기능
 */

public class EmailSender {

    public void sendMessage(String to, String message) {
        System.out.println("이메일 발송");
        System.out.println("수신 : " + to);
        System.out.println("메세지 : " + message);
    }
}
