package dill.exception;

/**
 * Represents an error that occurs during the execution of a command.
 */
public class ExecutionException extends DillException {
    public ExecutionException(String message) {
        super(message);
    }
}
