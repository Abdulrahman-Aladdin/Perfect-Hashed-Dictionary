package org.example.ADT;

import java.util.Random;

public class HashMap {
    public int[][] hashFunction;
    public int[] hashTable;
    int u , b , size;
    HashMap(int u , int b){
        this.u = u ;
        this.b = b;
        this.size = (int) Math.pow(Math.pow(2  , u), 2);
        this.hashTable = new int[size];
        for(int i = 0 ; i< this.size ; i++){
            this.hashTable[i] = -1;
        }
    }
    // contains basic methods
    void generate_hash(){
        this.hashFunction = new int[this.u][this.b];
        for(int i =0 ; i < this.u ; i++){
            for(int j = 0; j<this.b;j++){
                if((Math.random() >= .5))
                    this.hashFunction[i][j] = 1 ;
                else
                    this.hashFunction[i][j] = 0;

            }
        }
    }

    int hash(int x ){
        int[] temp = new int[this.b];
        int y = x;
        for(int i = 0 ; i< this.b;i++){
            temp[i] = y%2;
            y/=2;
        }
        int ans = 0;
        for(int i = 0 ; i <this.u ;i++){
            int s = 0;
            for(int j = 0; j< this.b;j++){
                s |= (this.hashFunction[i][j]&temp[j]);
            }
            if(s == 1)
                ans += Math.pow(2, this.u- i-1);

        }
        return  ans ;
    }


    void rehash(){
        generate_hash();
        int[] tempHashTable = new int[this.size];
        for(int i = 0 ; i < this.size ; i++){
            tempHashTable[i] = -1;
        }
        int temp = 0;
        for(int i = 0 ; i < this.size ; i++){
            if(this.hashTable[i] != -1){
                int x  = hash(this.hashTable[i]);
                if(tempHashTable[x] != -1){
                    temp = 1;
                    break;
                }
                tempHashTable[x] = this.hashTable[i];
            }
        }
        if(temp == 1){
            rehash();
            return;
        }

        this.hashTable = tempHashTable;
    }

    Boolean delete(int x){
        int index = hash(x);
        if(this.hashTable[index] != x){
            return false;
        }else{
            this.hashTable[index] = -1;
            return true;
        }
    }
    protected int stringToInt (String key) {
        return Math.abs (key.hashCode());
    }

}
