package org.example.Dictionary;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public interface IDictionary {

    Pair<Boolean,Integer> insert(String word);
    boolean delete(String word);
    boolean search(String word);

    Triple<Integer,Integer,Integer> batchInsert(String path);
    Pair<Integer,Integer> batchDelete(String path);
}
