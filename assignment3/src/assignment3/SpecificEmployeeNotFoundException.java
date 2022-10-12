package assignment3;

public class SpecificEmployeeNotFoundException extends RuntimeException {
    public SpecificEmployeeNotFoundException() { super("Employee was not registered yet.");}
    public SpecificEmployeeNotFoundException(String message) throws RuntimeException {
        super(message);
    }
}
