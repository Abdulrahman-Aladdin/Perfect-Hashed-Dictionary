package org.example.Analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class meanTimeCalculator {

    static int sizeSum = 0;

    public static void readNumbersFromFile(String fileName, List<Integer> numbers) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void getSize (List<Integer> s) {
        for (Integer x : s) {
            sizeSum += x;
        }
    }

    public static double getMean (List<Integer> x) {
        double res = 0;
        for (Integer i : x) {
            res += ((double) i / (double) sizeSum);
        }
        return res;
    }

    public static double getMeanSearch (List<Integer> x) {
        double res = 0;
        for (Integer i : x) {
            res += i;
        }
        return res / 12;
    }

    public static void main(String[] args) {
        List<Integer> insertion = new ArrayList<>();
        List<Integer> deletion = new ArrayList<>();
        List<Integer> search = new ArrayList<>();
        List<Integer> size = new ArrayList<>();
        String insert = "qinsertion.txt", delete = "qdeletion.txt", searchFile = "qsearch.txt", sizes = "qsize.txt";
        readNumbersFromFile(insert, insertion);
        readNumbersFromFile(delete, deletion);
        readNumbersFromFile(searchFile, search);
        readNumbersFromFile(sizes, size);
        getSize(size);
        double ins, del, s;
        ins = getMean (insertion);
        del = getMean (deletion);
        s = getMeanSearch (search);
        System.out.println(ins);
        System.out.println(del);
        System.out.println(s);
    }
}
