package org.example.ADT;


public class QuadraticHashMap implements IHashMap {
    private final HashMap hashMap;
    private int N = (int) 10e5; // default
    private final int u = Integer.SIZE;
    private final int b;

    public QuadraticHashMap(int N) {
        this.N = N;
        this.b = generateB(N);
        hashMap = new HashMap(u, b);
    }

    private int generateB(int N) {
        return (int) (Math.log(N) / Math.log(2));
    }

    @Override
    public boolean insert(String word) {
        int hashValue = hashMap.stringToInt(word);
        if (hashMap.search(hashValue)) {
            return false;
        }
        if(hashMap.hashTable[hashMap.hash(hashValue)] == -1) {
            hashMap.hashTable[hashMap.hash(hashValue)] = hashValue;
            return true;
        }
        /***
         * TODO: Implement case when hashTable[hash(hashValue)] is not empty
         */


        return true;
    }

    @Override
    public boolean delete(String word) {
        int hashValue = hashMap.stringToInt(word);
        if (hashMap.search(hashValue)) {
            return hashMap.delete(hashValue);
        }
        return false;
    }

    @Override
    public boolean search(String word) {
        int hashValue = hashMap.stringToInt(word);
        return hashMap.search(hashValue);
    }
}
