package demo2;

public class MessageService2 {

    private MessageSender sender;

    public void setSender(MessageSender sender) {
        this.sender = sender;
    }

    public void notice(String to, String text) {
        sender.send(to, text);
    }
}
