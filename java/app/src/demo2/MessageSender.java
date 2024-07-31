package demo2;

/*
   메세지 전송기능을 제공하는 구현클래스가 반드시 구현할 기능을 추상화
 */
public interface MessageSender {

    void send(String to, String message);
}
