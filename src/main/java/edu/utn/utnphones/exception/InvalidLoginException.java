package edu.utn.utnphones.exception;

public class InvalidLoginException extends Exception {

    public InvalidLoginException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return "Invalid login";
    }
}
