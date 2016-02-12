import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class MyHashMap<K,V> implements Map61B<K, V>{
    private double loadFactor = 0.75;
    private int size = 0;
    private int capacity=11;
    private HashSet<K> keySet;
    private ArrayList<Entry<K,V>> table;
    private int threshhold;

    public MyHashMap(){
        table = new ArrayList<Entry<K,V>>(capacity);
        keySet = new HashSet<K>(capacity);
        for(int i=0;i<capacity;++i){
            table.add(null);
        }
        threshhold =(int) (loadFactor * capacity);
    }
    public MyHashMap(int initialCapacity){
        capacity = initialCapacity;
        table = new ArrayList<Entry<K,V>>(capacity);
        keySet = new HashSet<K>(capacity);
        for(int i=0;i<capacity;++i){
            table.add(null);
        }
        threshhold =(int) (loadFactor * capacity);
    }
    public MyHashMap(int initialCapacity, float loadFactor){
        capacity = initialCapacity;
        this.loadFactor=(double)loadFactor;
        table = new ArrayList<Entry<K,V>>(capacity);
        keySet = new HashSet<K>(capacity);
        for(int i=0;i<capacity;++i){
            table.add(null);
        }
        threshhold =(int) (loadFactor * initialCapacity);
    }

    private class Entry<K,V>{
        private K key;
        private V val;
        private Entry<K,V> next;

        public Entry(K k, V v, Entry<K,V> next){
            key= k;
            val= v;
            this.next =next;
        }
    }

    @Override
    public void clear(){
        size = 0;
        table = new ArrayList<Entry<K,V>>(capacity);
        threshhold =(int) (loadFactor * capacity);
        keySet.clear();
        for(int i=0;i<capacity;++i){
            table.add(null);
        }
    }

    @Override
    public boolean containsKey(K key){
        return get(key)!=null;
    }

    @Override
    public V get(K key){
        if(size==0) return null;
        int hashcode = key.hashCode();
        int index = (hashcode & 0x7FFFFFFF) % capacity;
        for(Entry<K, V> e=table.get(index);e!=null;e=e.next){
            if(e.key.equals(key)){
                return e.val;
            }
        }
        return null;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void put(K key, V value){
        int hashcode = key.hashCode();
        int index = (hashcode & 0x7FFFFFFF) % capacity;
        //System.out.printf("thresh= %d, size= %d, index= %d, capacity= %d\n", threshhold, size, index, capacity);

        for(Entry<K, V> e=table.get(index);e!=null;e=e.next){
            if(e.key.hashCode()==hashcode&&e.key.equals(key)){
                e.val=value;
                return;
            }
        }

        Entry<K, V> e = table.get(index);
        table.add(index, new Entry<K, V>(key, value, e));
        size++;
        keySet.add(key);
        if(size>threshhold){
            rehash();
        }
    }

    public void rehash(){
        int oldCapacity = capacity;
        capacity= capacity*2+1;
        threshhold =(int)(capacity * loadFactor);
        ArrayList<Entry<K,V>> oldTable = table;
        clear();
        for(int i=0;i<oldCapacity;++i){
            for(Entry<K,V>e= oldTable.get(i);e!=null;e=e.next){
                put(e.key, e.val);
                //System.out.println(i);
            }
        }
    }

    @Override
    public V remove(K key){
        if(containsKey(key)==false) return null;
        int hashcode = key.hashCode();
        int index = (hashcode & 0x7FFFFFFF) % capacity;
        //I want a pointer!!
        Entry<K,V> e= table.get(index);
        if(e.key.hashCode()==hashcode&&e.key.equals(key)){
            V val=e.val;
            table.add(index, e.next);
            size--;
            keySet.remove(key);
            return val;
        }
         for(e=table.get(index);e.next!=null;e=e.next){
            if(e.next.key.hashCode()==hashcode&&e.next.key.equals(key)){
                V val = e.next.val;
                e.next=e.next.next;
                size--;
                keySet.remove(key);
                return val;
            }
        }
        return null;       
    }
    @Override
    public V remove(K key, V value){
        if(containsKey(key)==false) return null;
        int hashcode = key.hashCode();
        int index = (hashcode & 0x7FFFFFFF) % capacity;
        Entry<K,V> e= table.get(index);
        if(e.key.hashCode()==hashcode&&e.key.equals(key)&&e.val.equals(value)){
            V val=e.val;
            table.add(index, e.next);
            size--;
            keySet.remove(key);
            return val;
        }
         for(e=table.get(index);e.next!=null;e=e.next){
            if(e.next.key.hashCode()==hashcode&&e.next.key.equals(key)&&e.next.val.equals(value)){
                V val = e.next.val;
                e.next=e.next.next;
                size--;
                keySet.remove(key);
                return val;
            }
        }
        return null;   
    }
    @Override
    public Set<K> keySet(){
        return keySet;
    }
}