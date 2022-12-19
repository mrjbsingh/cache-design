package cache.exceptions;

public class StorageFullException extends RuntimeException{
    public StorageFullException(String msg) {
        super(msg);
    }

    public StorageFullException(){
        super("capacity full");
    }
}
