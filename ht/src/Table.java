public interface Table {
    void put(String key, String value);
    String get(String key);
    void remove(String key);
    int size();
}
