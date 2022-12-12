package storage;

public interface Storage<Key, Value> {
    void add(Key key, Value value);

    void remove(Key key);

    Value getValue(Key key);
}
