package org.example.ADT;


import java.util.ArrayList;
import java.util.Collections;

public class LinearHashMap implements IHashMap {

    private final HashMap hashMap;
    private final ArrayList<HashMap> outerTable;

    private int N = (int) 10e5; // default
    private final int u = Integer.SIZE;
    private final int b;

    private double loadFactor;

    public LinearHashMap(int N) {
        this.N = N;
        this.b = generateB(N);
        hashMap = new HashMap(u, b);
        outerTable = new ArrayList<>(Collections.nCopies(N, null));
    }

    public LinearHashMap() {
        this.b = generateB(N);
        hashMap = new HashMap(u, b);
        outerTable = new ArrayList<>(Collections.nCopies(N, null));
    }

    @Override
    public boolean insert(String word) {
        int val = hashMap.stringToInt(word);
        int outerIndex = hashMap.hash(val);

        if (outerTable.get(outerIndex) == null) outerTable.set(outerIndex, new HashMap(u, 0));

        HashMap innerTable = outerTable.get(outerIndex);
        int innerIndex = innerTable.hash(val);

        // element is already there so won't do anything
        if (innerTable.hashTable[innerIndex] == val)
            return false;


        // cell is empty so insertion is done directly
        if (innerTable.hashTable[innerIndex] == -1) {
            innerTable.hashTable[innerIndex] = val;
            innerTable.numOfElements++;
            return true;
        }

        // cell is not empty so we have to rehash
        if (innerTable.hashTable[innerIndex] != -1) {
            innerTable.rehash(generateB((innerTable.numOfElements + 1) * (innerTable.numOfElements + 1)));
            return insert(word);
        }

        return false;
    }

    @Override
    public boolean delete(String word) {
        int val = hashMap.stringToInt(word);
        int outerIndex = hashMap.hash(val);
        return outerTable.get(outerIndex).delete(val);
    }

    @Override
    public boolean search(String word) {
        int val = hashMap.stringToInt(word);
        int outerIndex = hashMap.hash(val);
        return outerTable.get(outerIndex).search(val);
    }

    private int generateB(int N) {
        return (int) (Math.log(N) / Math.log(2));
    }

    private void reHashTable(){

    }
}
