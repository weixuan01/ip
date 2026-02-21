package dill.exception;

/**
 * Represents an error that occurs when a file is detected to be corrupted during storage operations.
 */
public class CorruptedFileException extends StorageException {
    public CorruptedFileException(String message) {
        super(message);
    }
}
