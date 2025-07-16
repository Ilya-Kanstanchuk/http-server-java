package exception;

public class ServerMessageException extends Exception{
    public ServerMessageException() {
    }

    public ServerMessageException(String message) {
        super(message);
    }

    public ServerMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
