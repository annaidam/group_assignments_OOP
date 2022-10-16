package assignment3;

public class InvalidEmployeeException extends Exception {

    public InvalidEmployeeException(){
        super(" ");
    }

    public InvalidEmployeeException(String message) {
        super(message);
    }
}
