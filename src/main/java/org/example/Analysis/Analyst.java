package org.example.Analysis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Analyst {
    public ArrayList<Long> insertTimes;
    public ArrayList<Long> deleteTimes;
    public ArrayList<Long> searchTimes;
    public ArrayList<Long> meanSearch;
    public ArrayList<Long> size;
    long sizes;
    private static Analyst analyst;

    private Analyst () {
        insertTimes = new ArrayList<>();
        deleteTimes = new ArrayList<>();
        searchTimes = new ArrayList<>();
        size = new ArrayList<>();
        meanSearch = new ArrayList<>();
        sizes = 0;
    }

    public static Analyst getInstance() {
        if (analyst == null) {
            analyst = new Analyst();
        }
        return analyst;
    }

    private long getMeanTime (ArrayList<Long> arr) {
        long mean = 0;
        for (int i = 0; i < arr.size(); i++) {
            mean += (arr.get(i) * size.get(i));
        }
        return (mean / sizes);
    }

    private void getSizes () {
        for (Long aLong : size) {
            sizes += aLong;
        }
    }

    public void getResults () {
        System.out.println("Insertion: " + insertTimes.toString());
        System.out.println("Deletion: " + deleteTimes.toString());
        System.out.println("search: " + searchTimes.toString());
        System.out.println("meanSearch: " + meanSearch.toString());
        System.out.println("size: " + size.toString());
        getSizes();
        writeToFile(Long.toString(size.get(0)), "qsize.txt");
        long meanInsertionTime = getMeanTime(insertTimes);
        System.out.println("Mean Insertion time: " + meanInsertionTime);
        writeToFile(Long.toString(meanInsertionTime), "qinsertion.txt");
        long meanDeletionTime = getMeanTime(deleteTimes);
        System.out.println("Mean Deletion time: " + meanDeletionTime);
        writeToFile(Long.toString(meanDeletionTime), "qdeletion.txt");
        long meanSearchTime = getMeanSearchTime(searchTimes);
        System.out.println("Mean Search time: " + meanSearchTime);
        writeToFile(Long.toString(meanSearchTime), "qsearch.txt");
    }

    private static void writeToFile (String time, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(time + '\n');
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the words to file.");
        }
    }

    private long getMeanSearchTime(ArrayList<Long> searchTimes) {
        long mean = 0;
        for (Long x : searchTimes) {
            mean += x;
        }
        mean = (mean / searchTimes.size());
        return mean;
    }
}
