package org.example;

import org.example.CLI.CLI;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new CLI().run();
    }

    public static void write(){
        File file = new File("rand.txt");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < 5_000_000; i++) {
                bw.write(givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void read(){
        Set<String> set = new HashSet<>();

        File file = new File("rand.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            int size = 0;

            String line;
            while ((line = br.readLine()) != null && size <= 10_000_000) {
                size++;
                set.add(line);
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Set<Long> freq = new HashSet<>();

        set.forEach(a -> {
            long hash = stringToLong(a);
            freq.add(hash);
        });

        System.out.println(set.size());
        System.out.println(freq.size());
        System.out.println(set.size() - freq.size());

        if (set.size() - freq.size() != 0) System.exit(1);
    }

    public static long stringToLong(String k) {
        final long FNV_64_INIT = 0xcbf29ce484222325L;
        final long FNV_64_PRIME = 0x100000001b3L;
        long rv = FNV_64_INIT;
        final int len = k.length();
        for (int i = 0; i < len; i++) {
            rv ^= k.charAt(i);
            rv *= FNV_64_PRIME;
        }
        return rv;
    }


    public static String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) )
                .limit(random.nextInt(8) + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

