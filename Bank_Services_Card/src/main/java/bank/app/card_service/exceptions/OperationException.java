package bank.app.card_service.exceptions;

public class OperationException extends RuntimeException {

    public OperationException(String message) {
        super(message);
    }
}
