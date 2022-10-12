package assignment3;

public class EmployeeIDTakenException extends RuntimeException {
    public EmployeeIDTakenException() { super("Cannot register. The ID is already registered.");}
    public EmployeeIDTakenException(String message) throws RuntimeException {
        super(message);
    }
}