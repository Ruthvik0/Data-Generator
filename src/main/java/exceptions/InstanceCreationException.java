package exceptions;

public class InstanceCreationException extends RuntimeException{
    public InstanceCreationException() {
        super();
    }
    public InstanceCreationException(String message) {
        super(message);
    }
    public InstanceCreationException(Throwable cause) {
        super(cause);
    }
}
