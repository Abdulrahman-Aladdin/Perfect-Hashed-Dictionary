package Application;

public interface IDictionary {
    public boolean insert (String key);
    public boolean delete (String key);
    public boolean search (String key);
    public int[] batchInsert (String path);
    public int[] batchDelete (String path);
}
