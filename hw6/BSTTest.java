import static org.junit.Assert.*;
import org.junit.Test;

public class BSTTest{

    @Test
    public void testBasic(){
        BSTMap<Double, String> b=new BSTMap<Double, String>();
        b.put(10.0,"a");
        b.put(8.0 ,"b");
        b.put(12.0,"c");
        b.put(7.0,"d");
        b.put(9.0,"e");
        b.put(11.0,"f");
        b.put(14.0,"g");
        b.printInOrder();
        assertEquals("a", b.get(10.0));
        assertEquals(true, b.containsKey(7.0));
        assertEquals(7,b.size());
        b.clear();
        b.printInOrder();
        assertEquals(false, b.containsKey(7.0));
        
    }

    public static void main(String... args){
        jh61b.junit.textui.runClasses(BSTTest.class);
    }

}