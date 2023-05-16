package org.example.Dictionary;


import org.apache.commons.lang3.tuple.Pair;
import org.example.ADT.IHashMap;

import java.io.*;

public class Dictionary implements IDictionary {

    IHashMap map;

    public Dictionary(IHashMap map){
        this.map = map;
    }

    @Override
    public boolean insert(String word) {
        return map.insert(word);
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
    public Pair<Integer,Integer> batchInsert(String path) {

        int numberOfWordsInserted = 0;
        int numberOfWordsExisting = 0;

        try {
            File file = new File(path);

            if (file == null) return null;

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if (map.insert(line)) numberOfWordsInserted++;
                else numberOfWordsExisting++;
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Pair.of(numberOfWordsInserted,numberOfWordsExisting);
    }

    @Override
    public Pair<Integer,Integer> batchDelete(String path) {

        int numberOfWordsDeleted = 0;
        int numberOfWordsExisting = 0;

        try {
            File file = new File(path);

            if (file == null) return null;

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if (map.delete(line)) numberOfWordsDeleted++;
                else numberOfWordsExisting++;
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Pair.of(numberOfWordsDeleted,numberOfWordsExisting);
    }

}
