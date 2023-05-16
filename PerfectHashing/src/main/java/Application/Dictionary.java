package Application;

public class Dictionary implements IDictionary {
    public boolean insert(String key) {
        return false;
    }

    public boolean delete(String key) {
        return false;
    }

    public boolean search(String key) {
        return false;
    }

    public int[] batchInsert(String path) {
        return new int[0];
    }

    public int[] batchDelete(String path) {
        return new int[0];
    }
}
