import java.util.Arrays;

public class HashMap<K, V> implements MyMap<K, V> {
    private static final int defaultInitialCapacity = 4; //default hash table size. must be power of 2
    private static final int maxCapacity = 1 << 30; //max hash table size. same as 2^30
    private int capacity; //current hash table capacity. is a power of 2
    private static final float defaultMaxLoadFactor = 0.50f;
    private final float loadFactorThreshold; //specify load factor used in hash table
    private int size = 0; //number of entries in the map
    //ArrayList<Entry<K, V>> table; //hash table
    Entry<K, V>[] table;

    //constructor with default capacity and load factor
    public HashMap() {
        this(defaultInitialCapacity, defaultMaxLoadFactor);
    }

    //constructor with specified initial capacity and a default load factor
    public HashMap(int initialCapacity) {
        this(initialCapacity, defaultMaxLoadFactor);
    }

    //constructor with specified initial capacity and load factor
    public HashMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > maxCapacity)
            this.capacity = maxCapacity;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        table = new Entry[capacity];
    }

    //remove all entries from map
    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    //returns true if specified key is in map
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    //return true specified value is in map
    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && value.equals(table[i].getValue())) {
                return true;
            }
        }
        return false;
    }

    //return a set of entries in the map
    @Override
    public java.util.Set<Entry<K, V>> entrySet() {
        java.util.Set<Entry<K, V>> set = new java.util.HashSet<>();
        set.addAll(Arrays.asList(table).subList(0, capacity));
        return set;
    }

    //return value that matches specified key
    @Override
    public V get(K key) {
        int index = getIndex(key);
        if(index != -1){
            return table[index].getValue();
        }
        return null;
    }

    //return true if map is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //return set of all keys in this map
    @Override
    public java.util.Set<K> keySet() {
        java.util.Set<K> set = new java.util.HashSet<K>();

        for (Entry<K, V> entry : table) {
            set.add(entry.getKey());
        }
        return set;
    }

    //add an entry(key,value) to map (@ line 126)
    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        if (index != -1) { //if the key is already in the map
            V oldValue = table[index].getValue();
            table[index].value = value;
            return oldValue;
        }
        //check load factor
        if (size >= capacity * loadFactorThreshold) {
            if (capacity == maxCapacity)
                throw new RuntimeException("Exceeding maximum capacity");
            rehash();
        }

        for(int i = 0; i < capacity; i++) {
            index = hash(key.hashCode() + i);
            if(table[index] == null){
                //add new entry to hash table
                table[index] = new Entry<>(key, value);
                size++; //increase size
                return value;
            }
        }
        throw new RuntimeException("Exceeding capacity");
    }

    //remove entry for specified key (@ line 164)
    @Override
    public void remove(K key) {
        //remove first entry that matches key
        int index = getIndex(key);
        if(index != -1){
            table[index] = null;
            size--;
        }
    }

    //return number of entries in map
    @Override
    public int size() {
        return size;
    }

    //return a set of the values in this map
    @Override
    public java.util.Set<V> values() {
        java.util.Set<V> set = new java.util.HashSet<>();

        for (Entry<K, V> entry : table) {
            set.add(entry.getValue());
        }
        return set;
    }

    //hash function
    private int hash(int hashCode) {
       return Math.abs(hashCode % capacity);
    }

    private int getIndex(K key){
        for(int i = 0; i < capacity; i++) {
            int index = hash(key.hashCode() + i);
            if(table[index] != null && key.equals(table[index].getKey())){
                return index;
            }
        }
        return -1;
    }

    //returns a power of 2 for initial capacity
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1; //same as capacity *=2
        }
        return capacity;
    }

    //remove all entries from map
    private void removeEntries() {
        for(int i = 0; i < capacity; i++){
            table[i] = null;
        }
        size = 0;
    }

    //rehash
    private void rehash() {
        java.util.Set<Entry<K, V>> set = entrySet(); //retrieve entries
        capacity <<= 1; //same as capacity *= 2
        table = new Entry[capacity]; //new hash table
        size = 0; //reset size

        for (Entry<K, V> entry : set) {
            if(entry != null)
                put(entry.getKey(), entry.getValue()); //put entries into new table
        }
    }

    //String for this map
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for(Entry<K, V> entry: table){
            if(entry != null)
                builder.append(entry);
        }
        builder.append("]");
        return builder.toString();
    }
}
