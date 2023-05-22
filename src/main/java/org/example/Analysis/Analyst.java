package org.example.Analysis;

import java.util.ArrayList;

public class Analyst {
    public ArrayList<Long> insertTimes;
    public ArrayList<Long> deleteTimes;
    public ArrayList<Long> searchTimes;
    public ArrayList<Long> size;
    long sizes;
    private static Analyst analyst;

    private Analyst () {
        insertTimes = new ArrayList<>();
        deleteTimes = new ArrayList<>();
        searchTimes = new ArrayList<>();
        size = new ArrayList<>();
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
        System.out.println("size: " + size.toString());
        getSizes ();
        long meanInsertionTime = getMeanTime(insertTimes);
        System.out.println("Mean Insertion time: " + meanInsertionTime);
        long meanDeletionTime = getMeanTime(deleteTimes);
        System.out.println("Mean Deletion time: " + meanDeletionTime);
        long meanSearchTime = getMeanSearchTime(searchTimes);
        System.out.println("Mean Search time: " + meanSearchTime);
    }

    private long getMeanSearchTime(ArrayList<Long> searchTimes) {
        long res = 0;
        for (int i = 0; i < searchTimes.size(); i += 4) {
            double mean = 0;
            for (int j = i; j < i + 4; j++) {
                mean += (searchTimes.get(j) / 4.0);
            }
            res += (mean * size.get(i / 4));
        }
        return (res / sizes);
    }
}
