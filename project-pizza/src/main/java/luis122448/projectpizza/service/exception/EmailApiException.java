package luis122448.projectpizza.service.exception;

public class EmailApiException extends RuntimeException {

    public EmailApiException() {
        super("Error sending email ...");
    }
}
