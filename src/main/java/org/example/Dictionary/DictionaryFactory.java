package org.example.Dictionary;

import org.example.Map.HashMap;
import org.example.Map.IHashMap;
import org.example.Map.LinearHashMap;
import org.example.Map.QuadraticHashMap;

public class DictionaryFactory {

    public static Dictionary getDictionary(HashMap mapType){
        IHashMap map;
        map = switch (mapType){
            case Linear -> new LinearHashMap();
            case Quadratic -> new QuadraticHashMap();
        };
        return new Dictionary(map);
    }
}
