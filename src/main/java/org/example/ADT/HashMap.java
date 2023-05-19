package org.example.ADT;


import java.util.Arrays;

public class HashMap {

    public int[] hashTable;
    public int[][] hashFunction;
    int u, b, size;
    int numOfElements = 0;

    public HashMap(int u, int b) {
        this.u = u;
        this.b = b;
        this.size = (int) Math.pow(Math.pow(2, b), 2);
        this.hashTable = new int[size];
        Arrays.fill(this.hashTable, -1);
        generate_hash();
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

    int hash(int x) {
        int[] temp = new int[this.u];
        int y = x;
        for (int i = 0; i < this.u; i++) {
            temp[i] = y % 2;
            y /= 2;
        }
        int ans = 0;
        for (int i = 0; i < this.b; i++) {
            int s = 0;
            for (int j = 0; j < this.u; j++) {
                s |= (this.hashFunction[i][j] & temp[j]);
            }
            if (s == 1)
                ans += Math.pow(2, this.b - i - 1);

        }
        return ans;
    }


    void rehash(int b) {
        this.b = b;
        this.size = getSize(this.b);

        generate_hash();

        int[] tempHashTable = new int[this.size];
        Arrays.fill(tempHashTable,-1);

        int temp = 0;

        for (int value : this.hashTable) {
            if (value != -1) {
                int x = hash(value);
                if (tempHashTable[x] != -1) {
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

    boolean search(int x){
        int index = hash(x);
        return this.hashTable[index] == x;
    }

    boolean delete(int x) {
        int index = hash(x);
        if (this.hashTable[index] == x) {
            this.hashTable[index] = -1;
            return true;
        }
        return false;
    }

    protected int stringToInt(String key) {
        return Math.abs(key.hashCode());
    }

    protected long stringToLong(String key) {
        long hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = (hashVal << 4) + (key.charAt(i));
            long g = hashVal & 0xF0000000L;
            if (g != 0) hashVal ^= g >>> 24;
            hashVal &= ~g;
        }
        return hashVal;
    }

    private int getSize(int b){
        return (int) Math.pow(Math.pow(2, b), 2);
    }

}
