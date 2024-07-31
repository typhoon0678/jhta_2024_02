package demo2;

public class MessageService {

    private final MessageSender sender = new EmailMessageSender();

    public void notice(String to, String text) {
        sender.send(to, text);
    }
}
