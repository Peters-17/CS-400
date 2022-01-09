// --== CS400 Project One File Header ==--
// Name: Zhaowei Yin
// Email: zyin57@wisc.edu
// Team: Blue
// Group: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: Noun

import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {
    public boolean put(KeyType key, ValueType value);

    public ValueType get(KeyType key) throws NoSuchElementException;

    public int size();

    public boolean containsKey(KeyType key);

    public ValueType remove(KeyType key);

    public void clear();
}