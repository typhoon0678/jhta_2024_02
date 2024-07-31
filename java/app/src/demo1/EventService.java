package demo1;

/*
 * 고객들에게 이벤트 정보를 보내는 기능 구현
 */

public class EventService {

//    private EmailSender sender = new EmailSender();
    private SmsSender sender = new SmsSender();

    public void noticeSaleInfo(String message) {
//        sender.sendMessage("test@example.com", message);
        sender.sendSms("010-1234-5678", message);
    }
}
