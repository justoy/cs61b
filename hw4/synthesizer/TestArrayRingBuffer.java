package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(7);
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(1);
        arb.enqueue(10);
        assertEquals(7,arb.dequeue(),0.001);
        assertEquals(3,arb.dequeue(),0.001);
        assertEquals(2,arb.peek(),0.001);
        arb.enqueue(7.1);
        arb.enqueue(3.1);
        arb.enqueue(2.1);
        arb.enqueue(1.1);
        arb.enqueue(10.1);
        arb.enqueue(10.2);
        arb.enqueue(10.3);
        assertEquals(2,arb.peek(),0.001);
        assertEquals(2,arb.dequeue(),0.001);

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 