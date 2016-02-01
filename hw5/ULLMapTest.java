import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/** ULLMapTest. You should write additional tests.
 *  @author Josh Hug
 */

public class ULLMapTest {
    @Test
    public void testBasic() {
        ULLMap<String, String> um = new ULLMap<String, String>();
        um.put("Gracias", "Dios Basado");
        assertEquals(um.get("Gracias"), "Dios Basado");
        assertEquals(um.containsKey("Gracias"), true);
        assertEquals(um.containsKey("Graci"), false);
        assertEquals(um.size(), 1);
    }

    
    @Test
    public void testIterator() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        Iterator<Integer> umi = um.iterator();
        //System.out.println(umi.next());
        assertEquals(true,umi.hasNext());
        assertEquals(2, umi.next(),0.01);
        assertEquals(1, umi.next(),0.01);
        assertEquals(0, umi.next(),0.01);
        assertEquals(false,umi.hasNext());
    }

    @Test
    public void testInv(){
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        ULLMap<String,Integer>invUm= ULLMap.<String, Integer>invertMap(um);
        assertEquals(0, invUm.get("zero"),0.01);
        assertEquals(1, invUm.get("one"),0.01);
        assertEquals(2, invUm.get("two"),0.01);
    }
    

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(ULLMapTest.class);
    }
} 