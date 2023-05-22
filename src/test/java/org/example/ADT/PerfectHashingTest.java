package org.example.ADT;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerfectHashingTest {

    @Test
    // Test inserting a word into an empty LinearHashMap hash table
    public void testLinearHashingInsert() {
        LinearHashMap hashMap = new LinearHashMap(1);
        assertTrue(hashMap.insert("apple"));
        assertTrue(hashMap.search("apple"));
    }
    @Test
    // Test inserting a word into an empty Quadratic Hashing hash table
    public void testQuadraticHashingInsert() {
        QuadraticHashMap hashMap = new QuadraticHashMap(1);
        assertTrue(hashMap.insert("apple"));
        assertTrue(hashMap.search("apple"));
    }
    @Test
    // Test deleting a word from the LinearHashMap hash table
    public void testLinearHashingDelete() {
        LinearHashMap hashMap = new LinearHashMap(1);
        assertTrue(hashMap.insert("mango"));
        assertTrue(hashMap.delete("mango"));
        assertFalse(hashMap.search("mango"));
    }

    @Test
    // Test deleting a word from the Quadratic Hashing hash table
    public void testQuadraticHashingDelete() {
        QuadraticHashMap hashMap = new QuadraticHashMap(1);
        assertTrue(hashMap.insert("mango"));
        assertTrue(hashMap.delete("mango"));
        assertFalse(hashMap.search("mango"));
    }

    @Test
    // Test searching for a word in the LinearHashMap hash table
    public void testLinearHashingSearch() {
        LinearHashMap hashMap = new LinearHashMap(1);
        assertTrue(hashMap.insert("orange"));
        assertTrue(hashMap.search("orange"));
    }

    @Test
    // Test searching for a word in the Quadratic Hashing hash table
    public void testQuadraticHashingSearch() {
        QuadraticHashMap hashMap = new QuadraticHashMap(1);
        assertTrue(hashMap.insert("orange"));
        assertTrue(hashMap.search("orange"));
    }

    @Test
    // Test inserting and deleting multiple words in the LinearHashMap hash table
    public void testLinearHashingInsertDelete() {
        LinearHashMap hashMap = new LinearHashMap(10);
        assertTrue(hashMap.insert("apple"));
        assertTrue(hashMap.insert("banana"));
        assertTrue(hashMap.insert("cherry"));
        assertTrue(hashMap.delete("banana"));
        assertFalse(hashMap.search("banana"));
        assertTrue(hashMap.search("apple"));
        assertTrue(hashMap.search("cherry"));
    }

    @Test
    // Test inserting and deleting multiple words in the Quadratic Hashing hash table
    public void testQuadraticHashingInsertDelete() {
        QuadraticHashMap hashMap = new QuadraticHashMap(10);
        assertTrue(hashMap.insert("apple"));
        assertTrue(hashMap.insert("banana"));
        assertTrue(hashMap.insert("cherry"));
        assertTrue(hashMap.delete("banana"));
        assertFalse(hashMap.search("banana"));
        assertTrue(hashMap.search("apple"));
        assertTrue(hashMap.search("cherry"));
    }

    @Test
    // Test inserting a word that already exists in the hash table
    public void testInsertExistingWord() {
        QuadraticHashMap hashMap = new QuadraticHashMap(1);
        assertTrue(hashMap.insert("hello"));
        assertFalse(hashMap.insert("hello"));
    }

    @Test
    // Test deleting a non-existing word from the hash table
    public void testDeleteNonExistingWord() {
        QuadraticHashMap hashMap = new QuadraticHashMap(1);
        assertFalse(hashMap.delete("hello"));
    }

    @Test
    // Test searching for a non-existing word in the hash table
    public void testSearchNonExistingWord() {
        QuadraticHashMap hashMap = new QuadraticHashMap(2);
        assertFalse(hashMap.search("hello"));
    }

    @Test
    // Test inserting a large number of words into the LinearHashMap hash table
    public void testLinearHashMapInsertLargeNumber() {
        LinearHashMap hashMap = new LinearHashMap(1000);
        for (int i = 0; i < 1000; i++) {
            assertTrue(hashMap.insert("word" + i));
        }
        System.out.println(hashMap.getNumOfCollisions());
        assertEquals(1000, hashMap.getNumOfElements());
    }

    @Test
    // Test inserting a large number of words into the Quadratic Hashing hash table
    public void testQuadraticHashingInsertLargeNumber() {
        QuadraticHashMap hashMap = new QuadraticHashMap(1000);
        for (int i = 0; i < 1000; i++) {
            assertTrue(hashMap.insert("word" + i));
        }
        System.out.println(hashMap.getNumOfCollisions());
        assertEquals(1000, hashMap.getNumOfElements());
    }
    @Test
    // Test the efficiency of inserting and searching for words in the Quadratic Hashing hash table
    public void testQuadraticHashingEfficiency() {
        QuadraticHashMap hashMap = new QuadraticHashMap(10000);
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            assertTrue(hashMap.insert("word" + i));
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Quadratic Hashing Insertion Time: " + totalTime + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            assertTrue(hashMap.search("word" + i));
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Quadratic Hashing Searching Time: " + totalTime + " ns");
    }

    @Test
    // Test the efficiency of inserting and searching for words in the LinearHashMap hash table
    public void testLinearHashingEfficiency() {
        LinearHashMap hashMap = new LinearHashMap(10000);
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            assertTrue(hashMap.insert("word" + i));
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Linear Hashing Insertion Time: " + totalTime + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            assertTrue(hashMap.search("word" + i));
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Linear Hashing Searching Time: " + totalTime + " ns");
    }
}