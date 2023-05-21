package org.example.ADT;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Math.abs;

public class HashMap {

    public String[] hashTable;
    public int[][] hashFunction;
    int u;
    int b;
    int size;
    int numOfElements = 0;

    public HashMap(int u, int b) {
        this.u = u+1;
        this.b = b;
    }

    public void generate_hash() {
        this.hashFunction = new int[this.b][this.u];
        for (int i = 0; i < this.b; i++) {
            for (int j = 0; j < this.u; j++) {
                if ((Math.random() >= .5)) this.hashFunction[i][j] = 1;
                else this.hashFunction[i][j] = 0;
            }
        }
    }

    public int hash(long x) {
        int[] temp = new int[this.u];
        long y = abs(x);
        if(x < 0){
            temp[0] = 1;
        }else{
            temp[0] = 0;
        }
        for (int i = 1; i < this.u; i++) {
            temp[i] = (int)(y % 2);
            y /= 2;
        }
        int ans = 0;
        for (int i = 0; i < this.b; i++) {
            int s = 0;
            for (int j = 0; j < this.u; j++) {

                s ^= (temp[j] & this.hashFunction[i][j]);
            }
            if (s  == 1)
                ans += Math.pow(2, this.b - i - 1);

        }
        return ans;
    }


    public void rehash(int b) {
        this.b = b;
        this.setInnerTableSize();
        reHashHelper();
    }

    public void rehash() {
        reHashHelper();
    }


    public void reHashHelper() {
        generate_hash();
        String[] tempHashTable = new String[this.size];
        Arrays.fill(tempHashTable, null);

        int temp = 0;

        for (String value : this.hashTable) {
            if (value != null) {
                int x = hash(stringToLong(value));
                if (tempHashTable[x] != null) {
                    temp = 1;
                    break;
                }
                tempHashTable[x] = value;
            }
        }

        if (temp == 1) {
            rehash(b);
            return;
        }

        this.hashTable = tempHashTable;
    }

    boolean search(String word) {
        int index = hash(stringToLong(word));
        return Objects.equals(this.hashTable[index], word);
    }

    boolean delete(String word) {
        int index = hash(stringToLong(word));
        if (Objects.equals(this.hashTable[index], word)) {
            this.hashTable[index] = null;
            return true;
        }
        return false;
    }

    public long stringToLong(String k) {
        final long FNV_64_INIT = 0xcbf29ce484222325L;
        final long FNV_64_PRIME = 0x100000001b3L;
        long rv = FNV_64_INIT;
        final int len = k.length();
        for (int i = 0; i < len; i++) {
            rv ^= k.charAt(i);
            rv *= FNV_64_PRIME;
        }
        return rv;
    }

    public void setOuterTableSize() {
        this.size = (int) Math.pow(2, b);
    }

    public void setInnerTableSize() {
        this.size = (int) Math.pow(Math.pow(2, b), 2);
    }

    public void initializeTable() {
        this.hashTable = new String[size];
        Arrays.fill(this.hashTable, null);
        generate_hash();
    }


    public int getSize() {
        return size;
    }

}
