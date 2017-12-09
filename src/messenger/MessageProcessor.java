package messenger;

public interface MessageProcessor
{
    public void processMessage(String sender, String message);
    public void processError(String errorMessage);
    public void processInfo(String infoMessage);
}
