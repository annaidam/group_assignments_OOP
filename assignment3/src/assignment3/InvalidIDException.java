package assignment3;

public class InvalidIDException extends Exception {
    public InvalidIDException() {
        super("ID cannot be blank.");
    }
    public InvalidIDException(String message) throws Exception {
        super(message);
    }
    public InvalidNameException() {super("Name cannot be blank.");}
    public InvalidNameException(String message) throws Exception {
        super(message);
    }
    public NegativeSalaryException() {
        super("Salary must be greater than zero.");
    }
    public NegativeSalaryException(String message) throws Exception {
        super(message);
    }
}