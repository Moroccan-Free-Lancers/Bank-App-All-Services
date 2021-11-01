package bank.app.card_service.exceptions;

public class TransactionException extends RuntimeException {

    public TransactionException(String message) {
        super(message);
    }
}
