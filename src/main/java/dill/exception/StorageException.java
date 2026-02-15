package dill.exception;

/**
 * Represents an error that occurs during data storage operations.
 * Typically due to an invalid file path or issues reading/writing from/to the data storage file.
 */
public class StorageException extends DillException {
    private String successMessage;

    public StorageException(String message) {
        super(message);
    }

    public String getSuccessMessage() {
        return successMessage;
    }
}
