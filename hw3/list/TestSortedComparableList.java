import static org.junit.Assert.*;
import org.junit.Test;

public class TestSortedComparableList{
    
    @Test
    public void test1(){
        SortedComparableList l=new SortedComparableList(5,null);
        assertEquals(5, l.head);
        assertEquals(null,l.tail);
        SortedComparableList l2= new SortedComparableList();
        assertEquals(0,l2.head);
        assertEquals(null, l2.tail);
    }

    @Test
    public void test2(){
        SortedComparableList l=new SortedComparableList(5,null);
        assertEquals(5,l.get(0));
        l.insert(3);
        assertEquals(3,l.head);
        l.insert(10);
        assertEquals(5,l.tail.head);
        assertEquals(10,l.tail.tail.head);
        assertEquals(3,l.get(0));
        assertEquals(10,l.get(2));
        SortedComparableList l2=new SortedComparableList(1,null);
        l2.insert(7);
        l2.insert(12);
        l.extend(l2);
        assertEquals(1,l.get(0));
        assertEquals(3,l.get(1));
        assertEquals(12,l.get(5));
    }

    @Test
    public void test3(){
        SortedComparableList l=new SortedComparableList(5,null);
        l.insert(3);
        l.insert(10);
        SortedComparableList l2=SortedComparableList.subTail(l,1);
        l.insert(7);
        l2.insert(1);
        assertEquals(1,l2.head);
        assertEquals(3,l.head);
        assertEquals(10,l2.get(2));
    }

    @Test
    public void test4(){
        SortedComparableList l=new SortedComparableList(5,null);
        l.insert(3);
        l.insert(10);
        SortedComparableList l2=SortedComparableList.sublist(l,1,2);
        assertEquals(5,l2.get(0));
        assertEquals(10,l2.get(1));
    }

    @Test
    public void test5(){
        SortedComparableList l=new SortedComparableList(5,null);
        l.insert(3);
        l.insert(10);
        l.insert(12);
        SortedComparableList.expungeTail(l,1);
        assertEquals(3,l.get(0));
        assertEquals(5,l.get(1));
        assertEquals(null,l.tail.tail);
    }

    @Test
    public void test6(){
        SortedComparableList l= new SortedComparableList(5,null);
        l.insert(3);
        l.insert(3);
        l.insert(3);
        l.insert(10);
        l.insert(10);
        l.insert(12);
        l.insert(12);
        l.insert(12);
        l.squish();
        assertEquals(3,l.get(0));
        assertEquals(5,l.get(1));
        assertEquals(10,l.get(2));
        assertEquals(12,l.get(3));
    }

    @Test
    public void test7(){
        SortedComparableList l= new SortedComparableList(5,null);
        l.insert(3);
        l.insert(10);
        l.insert(12);
        l.twin();
        assertEquals("(3, 3, 5, 5, 10, 10, 12, 12)", l.toString());
    }


    public static void main(String... args){
        jh61b.junit.textui.runClasses(TestSortedComparableList.class);
    }
}