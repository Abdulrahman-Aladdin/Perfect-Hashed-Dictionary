package org.example.ADT;

public class HashMap {

    // contains basic methods
    protected int stringToInt (String key) {
        return Math.abs (key.hashCode());
    }
}
