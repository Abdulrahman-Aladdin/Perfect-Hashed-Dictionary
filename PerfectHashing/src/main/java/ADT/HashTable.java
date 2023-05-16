package ADT;

public class HashTable {
    // contains basic methods
    protected int stringToInt (String key) {
        return Math.abs (key.hashCode());
    }
}
