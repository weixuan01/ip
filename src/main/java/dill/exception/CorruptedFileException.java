package dill.exception;

public class CorruptedFileException extends StorageException {
    public CorruptedFileException(String message) {
        super(message);
    }
}
