package assignment3;

public class InvalidDepartmentException extends Exception {

    public InvalidDepartmentException() {
        super("Department must be one of the options: Business, Human Resources or Technical.");
    }
    public InvalidDepartmentException(String message)  {
        super(message);
    }
}
