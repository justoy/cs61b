import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.Iterator;
import java.util.NoSuchElementException;

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<K,V>  implements Map61B<K,V>, Iterable<K> {
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;
    private int size;

    public ULLMap(){
        front=null;
        size=0;
    }

    @Override
    public V get(K key) {
        return front.get(key).val;
    }

    @Override
    public void put(K key, V val) {
        //if key = null
        if(key==null||val==null){
            throw new NullPointerException("the key  or value to put() is null"); 
        }
        // if the key existed
        for(Entry x=front;x!=null;x=x.next){
            if(key.equals(x.key)){
                x.val=val;
                return;
            }
        }
        //else
        front=new Entry(key,val,front);
        ++size;
    }

    @Override
    public boolean containsKey(K key) {
        for(Entry x=front;x!=null;x=x.next){
            if(key.equals(x.key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        front=null;
        size=0;
    }

    @Override
    public Iterator<K> iterator(){
        return new ULLMapIter(front);
    }

    public static<V,K> ULLMap<V, K> invertMap(ULLMap<K, V> um){
        Iterator<K> umi = um.iterator();
        ULLMap<V, K> invUm=new ULLMap<V,K>();
        while(umi.hasNext()){
            K invVal=umi.next();
            V invKey=um.get(invVal);
            invUm.put(invKey, invVal);
        }
        return invUm;
    }


    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(K k,V v, Entry n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(K k) { //FIX ME
            //FILL ME IN (using equals, not ==)
            if(key.equals(k)){
                return this;
            }
            return next.get(k); //FIX ME
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private K key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private V val; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    private class ULLMapIter implements Iterator<K>{
        private Entry it;

        public ULLMapIter(Entry e){
            it=e;
        }

        @Override
        public boolean hasNext(){
            return it!=null;
        }

        @Override
        public K next(){
            if(it==null){
                throw new NoSuchElementException(); 
            }
            Entry now=it;
            it=it.next;
            return now.key;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Nice try, bozo."); // In java.lang
        }

    }
    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public V remove(K key) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key,V value) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }


}