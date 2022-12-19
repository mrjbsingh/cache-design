package cache.exceptions;

public class KeyNotFoundException extends RuntimeException{
    public KeyNotFoundException(String msg) {
        super(msg);
    }

    public KeyNotFoundException() {
        super("key not found ");
    }
}
