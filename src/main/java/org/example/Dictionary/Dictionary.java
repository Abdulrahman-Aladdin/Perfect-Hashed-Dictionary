package org.example.Dictionary;


import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.example.ADT.IHashMap;
import org.example.Analysis.Analyst;

import java.io.*;

public class Dictionary implements IDictionary {

    IHashMap map;
    Analyst analyst;
    public Dictionary(IHashMap map) {
        analyst = Analyst.getInstance();
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
        long start = System.nanoTime();
        boolean ret = map.search(word);
        long end = System.nanoTime();
        analyst.searchTimes.add(((end - start) / 1000));
        return ret;
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
            long start = System.nanoTime();
            while ((line = br.readLine()) != null) {
                map.setNumOfCollisions(0);
                if (map.insert(line)) {
                    numberOfWordsInserted++;
                    numberOfCollisions += map.getNumOfCollisions();
                } else numberOfWordsExisting++;
            }
            long end = System.nanoTime();
            analyst.insertTimes.add(((end - start) / 1000));
            analyst.size.add((long) numberOfWordsInserted);

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
            long start = System.nanoTime();
            while ((line = br.readLine()) != null) {
                if (map.delete(line)) numberOfWordsDeleted++;
                else numberOfWordsExisting++;
            }
            long end = System.nanoTime();
            analyst.deleteTimes.add(((end - start) / 1000));

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
