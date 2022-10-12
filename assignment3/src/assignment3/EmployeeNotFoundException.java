package assignment3;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("No employee has been registered yet.");
    }
    public EmployeeNotFoundException(String message) throws RuntimeException {
        super(message);
    }
}