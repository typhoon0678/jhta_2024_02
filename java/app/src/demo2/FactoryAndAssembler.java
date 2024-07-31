package demo2;

public class FactoryAndAssembler {

    public static void main(String[] args) {
        MessageService2 service = new MessageService2();

        EmailMessageSender email = new EmailMessageSender();
        SmsMessageSender sms = new SmsMessageSender();

        service.setSender(sms);
        service.notice("ABC", "50% Sale");
    }
}
