package exceptions;

public class NullProvidedException extends Exception {

    private String message = null;

    public NullProvidedException() {
        super();
    }

    public NullProvidedException(String message) {
        super(message);
        this.message = message;
    }

    public NullProvidedException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
