package dill.exception;

/**
 * Represents the base exception class for all errors specific to Dill.
 */
public class DillException extends Exception {
    public DillException(String message) {
        super(message);
    }
}
