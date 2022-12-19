package storage;

import cache.exceptions.KeyNotFoundException;
import cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashBasedStorage<Key, Value> implements Storage<Key, Value>{
    Map<Key, Value> storage;
    private final Integer capacity;

    public HashBasedStorage(Integer capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) {
        if(isStorageFull())
            throw new StorageFullException("Hash based storage capacity full");
        storage.put(key, value);
    }

    @Override
    public void remove(Key key) {
        if(!storage.containsKey(key))
            throw new KeyNotFoundException("key not found " + key);
        storage.remove(key);
    }

    @Override
    public Value getValue(Key key) {
        if(!storage.containsKey(key))
            throw new KeyNotFoundException("key doesn't exist in cache " + key);
        return storage.get(key);
    }

    private boolean isStorageFull(){
        return storage.size() == capacity;
    }
}
