package assignment3;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException() {
    /* super() here accesses "String message" in the constructor of the superclass "
Throwable" and gives it the value: "Error: Name cannot be empty"*/
        super("Name cannot be blank.");
    }
    public InvalidNameException(String message) throws RuntimeException {
        super(message);
    }
}

