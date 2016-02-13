import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Generic implementation of a hashtable with array in Java
 */
public class HashTable<K, V> {
    @SuppressWarnings("rawtypes")
    private Entry[] table;
    private int size;
    private int capacity;
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public HashTable(int capacity){
        this.capacity = capacity;
        table = new Entry[capacity];
        size = 0;
    }
    
    public HashTable(){
        this(DEFAULT_INITIAL_CAPACITY);
    }
    
    public int size(){
        return size;
    }
    
    private int hash(Object key){
        return key.hashCode() % capacity;
    }
    
    public V get(Object key){
        if (key == null) return getForNullKey();
        int hash = hash(key);
        for (Entry<K,V> e = table[hash(key)]; e!= null; e = e.next){
            Object k;
            if ((k = e.key) == key || key.equals(k))
                return e.value;
        }
        return null;
    }
    
    private V getForNullKey() {
        for (Entry<K,V> e = table[0]; e!= null; e = e.next){
            if (e.key == null)
                return e.value;
        }
        return null;
    }
    
    public V put(K key, V value) {
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key);
        for (Entry<K,V> e = table[hash]; e != null; e = e.next) {
            Object k;
            if ((k = e.key) == key || key.equals(k)){
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        
        addEntry(hash, key, value);
        return null;
    }
    
    private V putForNullKey(V value) {
        for (Entry<K,V> e = table[0]; e!= null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
    addEntry(0, null, value);
    return null;
    }
    
    void addEntry(int hash, K key, V value) {
        Entry<K,V> e = table[hash];
        table[hash] = new Entry<>(hash, key, value,e);
    }
       
    static class Entry<K, V> implements Map.Entry<K, V>{
        final K key;
        V value;
        Entry<K,V> next;
        final int hash;
        
        Entry(int h, K k, V v, Entry<K,V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        @Override
        public K getKey() {
            // TODO Auto-generated method stub
            return key;
        }

        @Override
        public V getValue() {
            // TODO Auto-generated method stub
            return value;
        }

        @Override
        public V setValue(V newvalue) {
            // TODO Auto-generated method stub
            V oldValue = value;
            value = newvalue;
            return oldValue;
        }
        
        public final int hashCode(){
           return (key==null ? 0 : key.hashCode()) ^ 
                   (value==null ? 0 : value.hashCode());
        }
        
    }
       /*public static void main (String[] args){
           String k = "abc";
           String m = k.toUpperCase();
           System.out.println(m);
           System.out.println(k);
           List a = new ArrayList<String>();
           List c = new ArrayList<String>();
           a.add("first");
           List b = a;
           a.add("second");
           c.add("third");
           a = c;
           System.out.println(a);
           System.out.println(b);
           System.out.println(c);
       }*/
}