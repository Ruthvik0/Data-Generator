package exceptions;

public class SetterInvokingException extends RuntimeException{
    public SetterInvokingException() {
        super();
    }
    public SetterInvokingException(String message) {
        super(message);
    }
    public SetterInvokingException(Throwable cause) {
        super(cause);
    }
}
