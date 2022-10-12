package assignment3;

public class InvalidDegreeException extends Exception{
    public InvalidDegreeException(){
        super("Degree must be one of the options: BSc, MSc or PhD.");
    }
    public InvalidDegreeException(String message) throws Exception {
        super(message);
    }
}