public interface MyMap <K, V> {
    //remove all entries from map
    public void clear();

    //return true if specified key is in map
    public boolean containsKey(K key);

    //return true if map contains specified value
    public boolean containsValue(V value);

    //return set of entries in map
    public java.util.Set<Entry<K, V>> entrySet();

    //return value that matches specified key
    public V get(K key);

    //return true if map has no entries
    public boolean isEmpty();

    //return a set with all the keys in this map
    public java.util.Set<K> keySet();

    //add an entry (key, value) into the map
    public V put(K key, V value);

    //remove an entry for the specified key
    public void remove(K key);

    //return the number of mappings in this map
    public int size();

    //return a set with all the values in this map
    public java.util.Set<V> values();

    //define inner class for Entry
    public static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }

        @Override
        public String toString(){
            return "[" + key + ", " + value + "]";
        }
    }
}
