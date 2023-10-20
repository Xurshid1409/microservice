package uz.jurayev.account.exception;

public class CustomerExistException extends RuntimeException {

    public CustomerExistException(String message) {
        super(message);
    }

    public CustomerExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
