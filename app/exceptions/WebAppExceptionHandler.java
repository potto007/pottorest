package exceptions;

public class WebAppExceptionHandler {
    private String errorMessage;

    public WebAppExceptionHandler(int status, String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
