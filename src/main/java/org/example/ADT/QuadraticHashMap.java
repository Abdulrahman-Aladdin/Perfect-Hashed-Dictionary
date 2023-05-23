
package org.example.ADT;

import static java.lang.Math.ceil;

public class QuadraticHashMap implements IHashMap {
    private HashMap hashMap;
    private int N = (int) 10e5; // default
    private final int u = Long.SIZE;
    private final int b;

    public QuadraticHashMap(int N) {
        this.N = N;
        this.b = generateB(N * N);
        this.initializeOuterTable();
    }

    private void initializeOuterTable() {
        hashMap = new HashMap(u, b);
        hashMap.setOuterTableSize();
        hashMap.initializeTable();
    }

    private int generateB(int N) {
        return (int) ceil(Math.log(N) / Math.log(2));
    }

    @Override
    public boolean insert(String word) {
        long hashValue = hashMap.stringToLong(word);
        if (hashMap.search(word)) {
            return false;
        }
        if (hashMap.hashTable[hashMap.hash(hashValue)] == null) {
            hashMap.hashTable[hashMap.hash(hashValue)] = word;
            hashMap.numOfElements++;
        } else {
            hashMap.rehash();
            hashMap.numOfCollisions++;
            insert(word);
        }
        return true;
    }

    @Override
    public boolean delete(String word) {
        if (hashMap.search(word)) {
            if (hashMap.delete(word)) {
                hashMap.numOfElements--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean search(String word) {
        return hashMap.search(word);
    }

    public int getNumOfElements() {
        return hashMap.numOfElements;
    }

    public int getNumOfCollisions() {
        return hashMap.numOfCollisions;
    }

    @Override
    public void setNumOfCollisions(int numOfCollisions) {
        hashMap.numOfCollisions = numOfCollisions;
    }

    @Override
    public int calculateTotalSize() {
        return hashMap.size;
    }
}