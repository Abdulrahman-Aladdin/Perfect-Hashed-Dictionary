package org.example.Dictionary;


import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.example.ADT.IHashMap;

import java.io.*;

public class Dictionary implements IDictionary {

    IHashMap map;

    public Dictionary(IHashMap map) {
        this.map = map;
    }

    @Override
    public Pair<Boolean, Integer> insert(String word) {
        map.setNumOfCollisions(0);
        return Pair.of(map.insert(word), map.getNumOfCollisions());
    }

    @Override
    public boolean delete(String word) {
        return map.delete(word);
    }

    @Override
    public boolean search(String word) {
        return map.search(word);
    }

    @Override
    public Triple<Integer, Integer, Integer> batchInsert(String path) {

        int numberOfWordsInserted = 0;
        int numberOfWordsExisting = 0;
        int numberOfCollisions = 0;

        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                map.setNumOfCollisions(0);
                if (map.insert(line)) {
                    numberOfWordsInserted++;
                    numberOfCollisions += map.getNumOfCollisions();
                } else numberOfWordsExisting++;
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Triple.of(numberOfWordsInserted, numberOfWordsExisting, numberOfCollisions);
    }

    @Override
    public Pair<Integer, Integer> batchDelete(String path) {

        int numberOfWordsDeleted = 0;
        int numberOfWordsExisting = 0;

        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if (map.delete(line)) numberOfWordsDeleted++;
                else numberOfWordsExisting++;
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Pair.of(numberOfWordsDeleted, numberOfWordsExisting);
    }

}
