package exception;

public class ServerAddressException extends Exception{
    public ServerAddressException() {
    }

    public ServerAddressException(String message) {
        super(message);
    }

    public ServerAddressException(String message, Throwable cause) {
        super(message, cause);
    }
}
