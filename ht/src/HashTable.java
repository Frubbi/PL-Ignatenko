public class HashTable implements Table {

    private static class Entry {
        String key;
        String value;
        boolean isDeleted;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry[] table;
    private int size;

    public HashTable() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private int hashFunc(String key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public void put(String key, String value) {
        if ((double) size / table.length >= LOAD_FACTOR) {
            resize();
        }

        int index = hashFunc(key); //хеширование ключа
        int startIndex = index;

        do {
            Entry entry = table[index];

            if (entry == null || entry.isDeleted) {
                table[index] = new Entry(key, value);
                size++;
                return;
            } else if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }

            index = (index + 1) % table.length;
        } while (index != startIndex);
    }

    @Override
    public String get(String key) {
        int index = hashFunc(key);
        int startIndex = index;

        do {
            Entry entry = table[index];
            if (entry == null) {
                return null;
            }
            if (!entry.isDeleted && entry.key.equals(key)) {
                return entry.value;
            }
            index = (index + 1) % table.length;
        } while (index != startIndex);

        return null;
    }

    @Override
    public void remove(String key) {
        int index = hashFunc(key);
        int startIndex = index;

        do {
            Entry entry = table[index];
            if (entry == null) {
                return;
            }
            if (!entry.isDeleted && entry.key.equals(key)) {
                entry.isDeleted = true;
                size--;
                return;
            }
            index = (index + 1) % table.length;
        } while (index != startIndex);
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        Entry[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;

        for (Entry entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                put(entry.key, entry.value);
            }
        }
    }
}
