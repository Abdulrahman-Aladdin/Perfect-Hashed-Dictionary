package org.example.ADT;


import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class LinearHashMap implements IHashMap {

    private HashMap hashMap;
    private final ArrayList<HashMap> outerTable;
    private int N = (int) 10e5; // default
    private final int u = Long.SIZE;
    private final int b;

    public LinearHashMap(int N) {
        this.N = N;
        this.b = generateB(N);
        this.initializeOuterTable();
        outerTable = new ArrayList<>(Collections.nCopies(hashMap.size, null));
    }

    public LinearHashMap() {
        this.b = generateB(N);
        this.initializeOuterTable();
        outerTable = new ArrayList<>(Collections.nCopies(hashMap.size, null));
    }

    @Override
    public boolean insert(String word) {

        long val = hashMap.stringToLong(word);
        int outerIndex = hashMap.hash(val);
        if (outerTable.get(outerIndex) == null) {
            outerTable.set(outerIndex, new HashMap(u, 0));
            outerTable.get(outerIndex).setInnerTableSize();
            outerTable.get(outerIndex).initializeTable();
        }

        HashMap innerTable = outerTable.get(outerIndex);
        int innerIndex = innerTable.hash(val);

        // element is already there so won't do anything
        if (Objects.equals(innerTable.hashTable[innerIndex], word))
            return false;


        // cell is empty so insertion is done directly
        if (innerTable.hashTable[innerIndex] == null) {
            innerTable.hashTable[innerIndex] = word;
            innerTable.numOfElements++;
            hashMap.numOfElements++;
            return true;
        }

        // numOfElements + 1 -> (numOfElements + 1)^2 -> to first power of 2 >= -> N^2 -> b = log2(N^2)
        // cell is not empty , so we have to rehash
        if (innerTable.hashTable[innerIndex] != null) {
            hashMap.numOfCollisions++;
            int newB = generateB(findNearestPowerOf2(
                            (innerTable.numOfElements + 1) * (innerTable.numOfElements + 1)));

            innerTable.rehash(newB);
            return insert(word);
        }

        return false;
    }

    public Pair<Integer, Integer> batchInsert(ArrayList<String> strings) {

        int numberOfWordsInserted = 0;
        int numberOfWordsExisting = 0;

        ArrayList<ArrayList<String>> tempTable = new ArrayList<>(Collections.nCopies(N, null));

        Set<Integer> set = new HashSet<>();

        for (String string : strings) {
            long val = hashMap.stringToLong(string);
            int index = hashMap.hash(val);

            if (tempTable.get(index) == null) tempTable.set(index,new ArrayList<>());

            tempTable.get(index).add(string);
            set.add(index);
        }


        for (var i : set) {
            var list = tempTable.get(i);

            if (list.size() != 0) {

                outerTable.set(i, new HashMap(u, generateB(list.size())));
                outerTable.get(i).setInnerTableSize();
                outerTable.get(i).initializeTable();

                for (var string : list) {
                    boolean inserted = insert(string);
                    if (inserted) numberOfWordsInserted++;
                    else numberOfWordsExisting++;
                }
            }
        }

        return Pair.of(numberOfWordsInserted,numberOfWordsExisting);
    }

    @Override
    public boolean delete(String word) {

        if (!search(word)) return false;

        long val = hashMap.stringToLong(word);
        int outerIndex = hashMap.hash(val);
        HashMap innerTable = outerTable.get(outerIndex);

        if (innerTable.delete(word)){
            innerTable.numOfElements--;
            return true;
        }

        return false;
    }

    @Override
    public boolean search(String word) {
        long val = hashMap.stringToLong(word);
        int outerIndex = hashMap.hash(val);
        return outerTable.get(outerIndex) != null && outerTable.get(outerIndex).search(word);
    }

    private int generateB(int N) {
        return (int) Math.ceil(Math.log(N) / Math.log(2));
    }

    private void initializeOuterTable() {
        hashMap = new HashMap(u, b);
        hashMap.setOuterTableSize();
        hashMap.initializeTable();
    }

    private int findNearestPowerOf2(int number){
        int rounded = 1;
        while (rounded < number) {
            rounded <<= 1;
        }
        return rounded;
    }

    public int getNumOfElements(){
        return hashMap.numOfElements;
    }

    public int getNumOfCollisions() {
        return hashMap.numOfCollisions;
    }

    public void setNumOfCollisions(int numOfCollisions) {
        hashMap.numOfCollisions = numOfCollisions;
    }

}
