package cache;

import cache.exceptions.KeyNotFoundException;
import cache.exceptions.StorageFullException;
import evictionpolicy.EvictionPolicy;
import storage.Storage;

public class Cache<Key, Value> {

    private Storage<Key, Value> storage;

    private EvictionPolicy<Key> evictionPolicy;

    Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy){
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    void put(Key key, Value value){
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        }catch (StorageFullException storageFullException){
            Key keyToRemove = this.evictionPolicy.evictKey();
            if(keyToRemove == null){
                throw new RuntimeException("Unexpected state. Storage full but no key to evict.");
            }
            this.storage.remove(key);
            System.out.println("Removing key to evict space " + keyToRemove);
            put(key, value);
        }
    }

    Value get(Key key){
        Value value = null;
        try {
            value = this.storage.getValue(key);
            this.evictionPolicy.keyAccessed(key);
        }catch (KeyNotFoundException keyNotFoundException){
            System.out.println("Unable to find the key " + key);
        }
        return value;
    }

}
