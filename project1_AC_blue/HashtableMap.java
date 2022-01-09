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
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * HashtableMap class implements MapADT to write a hashtable
 * @param <KeyType> a type of key that is used to identify the value
 * @param <ValueType> a type of value that is stored in LinkedList
 */
public class HashtableMap <KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    private LinkedList<Node<KeyType, ValueType>>[] hashArray;
    //the max size of array
    private int capacity;

    //the actual size that has been occupied in array
    private int size;

    //constant value
    final int defaultCapacity = 20;
    final double loadFactor = 0.8;

    /**
     * constructor with parameter capacity
     * @param capacity the biggest size of array
     */
    @SuppressWarnings("unchecked")
    public HashtableMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        //create a new array that is going to store a LinkedList in each index of array
        this.hashArray = (LinkedList<Node<KeyType, ValueType>>[]) new LinkedList[this.capacity];
        for(int i = 0; i < this.capacity; i++) {
            hashArray[i] = new LinkedList<>();
        }
    }

    /**
     * default constructor
     */
    @SuppressWarnings("unchecked")
    public HashtableMap() { // with default capacity = 20
        this.capacity = defaultCapacity;
        this.size = 0;
        //create a new array that is going to store a LinkedList in each index of array
        this.hashArray = (LinkedList<Node<KeyType, ValueType>>[]) new LinkedList[this.capacity];
        for(int i = 0; i < this.capacity; i++){
            hashArray[i] = new LinkedList<>();
        }
    }

    /**
     * put method
     * @param key key is corresponding to a value for each hash-target
     * @param value a hash-value stored in LinkedList by relating with a key
     * @return true if successfully add into, otherwise false
     */
    @Override
    @SuppressWarnings("unchecked")
   public boolean put(Object key, Object value) {
        //check if key is null or already exist
        if (key == null || containsKey(key)) {
            return false;
        }
        int hashIndex = (Math.abs(key.hashCode())) % capacity;
        //check whether hashArray is empty, if so then initialize it
        if(hashArray[hashIndex] == null){
            //create a new Linked List
            hashArray[hashIndex] = new LinkedList<>();
        }
        //hashArray is not empty so we add Node at first available place
        hashArray[hashIndex].add(0, new Node(key, value));
        size++;
        if(isOverSize()){
            rehash();
        }
        return true;
    }

    /**
     * this method check if the loadFactor exceeds limited loadFactor
     * @return true if oversize, otherwise false
     */
    public boolean isOverSize(){
        return (double) this.size / (double) this.capacity >= loadFactor;
    }

    /**
     * this method is to find a value related to a key and return the value
     * @param key a key symbol to find corresponding value
     * @return value if we find it, return null if we don't
     * @throws NoSuchElementException stop running program and print out an error message
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object get(Object key) throws NoSuchElementException {
        //exception if key is null
        if (key == null) {
            throw new NoSuchElementException("get method error: key is null, no such element");
        }
        int hashIndex = (Math.abs(key.hashCode())) % capacity;
        LinkedList<Node<KeyType, ValueType>> tempLinkedList = hashArray[hashIndex];
        //exception if tempLinkedList is null
        if (tempLinkedList == null) {
            throw new NoSuchElementException("get method error: No such element");
        }
        //check if we can find target key
        for (Node<KeyType, ValueType> tempNode : tempLinkedList) {
            if (tempNode.getKey().equals(key)) {
                return tempNode.getValue();
            }
        }
        //exception
        throw new NoSuchElementException("get method error: no such element");
    }

    /**
     * size method to return how many LinkedLists stored in array
     * @return the number of LinkedLists that stored in array
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * this method is to check if the array contains the key
     * @param key a KeyType key as a target to find
     * @return true if array has such key, otherwise return false
     */
    @Override
    public boolean containsKey(Object key) {
        int hashIndex = (Math.abs(key.hashCode())) % capacity;
        LinkedList<Node<KeyType, ValueType>> tempLinkedList = hashArray[hashIndex];
        //check if tempLinkedList is empty
        if (tempLinkedList == null) {
            return false;
        }
        //iterate to find target key
        for (Node<KeyType, ValueType> tempNode : tempLinkedList) {
            if (tempNode.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get the LinkedList that stored in the array of index i
     * @return create a new hashArray
     */
    public LinkedList<Node<KeyType, ValueType>> getLinkedList(int i) {
        return this.hashArray[i];
    }

    /**
     * this method is to remove a element in LinkedList in the array and return
     * the value that stored in this element
     * @param key a KeyType key as a target to find
     * @return the value that stored in this element
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object remove(Object key) {
        //check if key is null
        if (key == null) {
            return null;
        }
        //find target index
        int hashIndex = (Math.abs(key.hashCode())) % capacity;
        //notice this is not copy, tempLinkedList is the LinkedList stored in hashArray
        LinkedList<Node<KeyType, ValueType>> targetLinkedList = hashArray[hashIndex];
        if (targetLinkedList == null) {
            return null;
        }
        //iterate to remove target key
        for (int i = 0; i < targetLinkedList.size(); i++) {
            if (targetLinkedList.get(i).getKey().equals(key)) {
                Object value = targetLinkedList.get(i).getValue();
                targetLinkedList.remove(i);
                this.size--;
                return value;
            }
        }
        return null;

    }

    /**
     * This method is to set hashArray empty
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.capacity; i++) {
            hashArray[i] = null;
        }
        this.size = 0;
    }

    /**
     * this method is to double the capacity when loadFactor reach to 80%
     */
    @SuppressWarnings("unchecked")
    public void rehash(){
        int oldCapacity = this.capacity;
        int newCapacity = this.capacity * 2;
        this.capacity = 2 * this.capacity;//double the capacity to rehash
        LinkedList<Node<KeyType, ValueType>>[] newArray
                = (LinkedList<Node<KeyType, ValueType>>[]) new LinkedList[oldCapacity];
        //copy all contents in newArray
        System.arraycopy(hashArray, 0, newArray, 0, oldCapacity);
        //reset the original array with new capacity
        this.hashArray = (LinkedList<Node<KeyType, ValueType>>[]) new LinkedList[newCapacity];
        //put Node from newArray to hashArray by adding and deleting one by one
        for(int j = 0; j < oldCapacity; j++){
            //judge whether it's null in index j
            if(newArray[j] == null){
                continue;
            }
            //iterate
            LinkedList<Node<KeyType, ValueType>> tempLinkedList = newArray[j];
            for (int k = 0; k < tempLinkedList.size(); k++) {
                //store Node in index k firstly before remove
                Node<KeyType, ValueType> tempNode = tempLinkedList.get(k);
                tempLinkedList.remove(k);
                //put key and value from tempNode to hashArray (original array)
                put(tempNode.getKey(), tempNode.getValue());
                size--;//this process of transferring content didn't need to increase size
                k--;
            }
        }
    }

}
