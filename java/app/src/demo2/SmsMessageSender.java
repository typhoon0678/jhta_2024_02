package demo2;

public class SmsMessageSender implements MessageSender {

    @Override
    public void send(String to, String message) {
        System.out.println("SMS 발송");
        System.out.println("받는 사람 : " + to);
        System.out.println("내용 : " + message);
    }
}
