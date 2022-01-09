//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: CS400 Project1
// Course: CS 400 Fall 2021
//
// Author: Yuan Haoyang
// Email: hyuan73@wisc.edu
// Lecturer: GARY DAHL
//
///////////////////////////////////////////////////////////////////////////////
/**
 * this class creates a class named Node to contains KeyType and ValueType
 * @param <KeyType> a type of key that is used to identify the value
 * @param <ValueType> a type of value that is stored in LinkedList
 */
public class Node <KeyType, ValueType>{
    //objects properties
    private KeyType key;
    private ValueType value;

    /**
     * constructor for Node
     * @param key a type of key that is used to identify the value
     * @param value a type of value that is stored in LinkedList
     */
    public Node(KeyType key, ValueType value){
        this.key = key;
        this.value = value;
    }

    /**
     * getMethod for key
     * @return key of this object
     */
    public KeyType getKey() {
        return key;
    }

    /**
     * getMethod for value
     * @return value of this object
     */
    public ValueType getValue() {
        return value;
    }

    /**
     * setMethod for key
     * @param key a type of key that is used to identify the value
     */
    public void setKey(KeyType key) {
        this.key = key;
    }

    /**
     * setMethod for value
     * @param value a type of value that is stored in LinkedList
     */
    public void setValue(ValueType value) {
        this.value = value;
    }

}

