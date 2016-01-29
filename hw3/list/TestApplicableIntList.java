import static org.junit.Assert.*;
import org.junit.Test;

public class TestApplicableIntList{
    
    @Test
    public void test1(){
        ApplicableIntList l=new ApplicableIntList(5,null);
        assertEquals(5, l.head);
        assertEquals(null,l.tail);
        ApplicableIntList l2= new ApplicableIntList();
        assertEquals(0,l2.head);
        assertEquals(null, l2.tail);
    }

    @Test
    public void test2(){
        ApplicableIntList l=new ApplicableIntList(5,null);
        assertEquals(5,l.get(0));
        l.insert(3);
        assertEquals(3,l.head);
        l.insert(10);
        assertEquals(5,l.tail.head);
        assertEquals(10,l.tail.tail.head);
        assertEquals(3,l.get(0));
        assertEquals(10,l.get(2));
    }

    public static void main(String... args){
        jh61b.junit.textui.runClasses(TestApplicableIntList.class);
    }
}