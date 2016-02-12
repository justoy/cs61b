import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest{

    @Test
    public void basicTest(){
        MyHashMap<Integer, String> mp = new MyHashMap<Integer, String>();
        mp.put(1,"lsj1");
        mp.put(11,"lsj11");
        mp.put(5,"lsj3");
        mp.put(4,"lsj4");
        mp.put(3,"lsj5");
        mp.put(1,"lsj01");

        assertEquals(5,mp.size());
        assertEquals("lsj01", mp.get(1));
        assertEquals("lsj11", mp.get(11));
        assertEquals("lsj01",mp.remove(1));
        assertEquals(null,mp.remove(1,"lsj"));
        assertEquals(null, mp.get(1));

    }

    public static void main(String... args) {
        jh61b.junit.textui.runClasses(HashMapTest.class);
    }
}