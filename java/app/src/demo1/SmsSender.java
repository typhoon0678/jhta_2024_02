package demo1;

public class SmsSender {

    public void sendSms(String tel, String message) {
        System.out.println("문자 발송");
        System.out.println("받는 사람 : " + tel);
        System.out.println("메세지 : " + message);
    }
}
