package org.example.Dictionary;

import org.example.ADT.HashMapSpace;
import org.example.ADT.IHashMap;
import org.example.ADT.LinearHashMap;
import org.example.ADT.QuadraticHashMap;

public class DictionaryFactory {

    public static Dictionary getDictionary(HashMapSpace mapType){
        IHashMap map;
        map = switch (mapType){
            case Linear -> new LinearHashMap();
            case Quadratic -> new QuadraticHashMap();
        };
        return new Dictionary(map);
    }
}
