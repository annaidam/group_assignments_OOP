package assignment3;

public class InvalidIDException extends RuntimeException {
    public InvalidIDException() {
        super("ID cannot be blank.");
    }
    public InvalidIDException(String message) throws RuntimeException {
        super(message);
    }
}
