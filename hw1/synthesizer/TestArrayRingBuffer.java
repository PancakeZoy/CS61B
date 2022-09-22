package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        BoundedQueue<Double> arb = new ArrayRingBuffer<>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(1.1);
        arb.enqueue(2.2);
        arb.enqueue(3.3);
        assertTrue(arb.isFull());
        assertEquals((Double) 1.1, arb.dequeue());
        assertFalse(arb.isFull());
        assertEquals((Double) 2.2, arb.peek());
        arb.enqueue(4.4);
        assertTrue(arb.isFull());
    }

    @Test
    public void finaltest() {
        BoundedQueue<Integer> A = new ArrayRingBuffer<>(5);
        A.enqueue(1);
        A.enqueue(3);
        A.enqueue(5);
    }
}
