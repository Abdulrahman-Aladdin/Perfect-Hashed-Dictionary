package org.example.ADT;


public interface IHashMap {

    boolean insert(String word);

    boolean delete(String word);

    boolean search(String word);

    int getNumOfCollisions();

    void setNumOfCollisions(int numOfCollisions);

    int calculateTotalSize();
}
