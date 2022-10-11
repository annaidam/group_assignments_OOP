package assignment3;

public class NegativeSalaryException extends RuntimeException {
    public NegativeSalaryException() {
        super("Salary must be greater than zero.");
    }
    public NegativeSalaryException(String message) throws RuntimeException {
        super(message);
    }
}
