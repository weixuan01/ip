package dill.exception;

/**
 * Represents an error that occurs when the user input cannot be parsed due to syntax issues.
 */
public class InvalidCommandException extends DillException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
