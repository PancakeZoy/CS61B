package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity =  capacity;
    }

    private int moveRight(int index){
        if (index==capacity-1){
            return 0;
        }else{
            return index + 1;
        }
    }

    @Override
    public int capacity(){
        return capacity;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */

    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = moveRight(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T value = rb[first];
        rb[first] = null;
        first = moveRight(first);
        fillCount -= 1;
        return value;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof ArrayRingBuffer other){
            if (this.fillCount() != other.fillCount()){
                return false;
            }
            Iterator<T> myItr = this.iterator();
            Iterator<T> otherItr = other.iterator();
            for (int i=0; i<this.fillCount(); i++){
                if (myItr.next() != otherItr.next()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Iterator<T> iterator(){
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T>{
        private int ptr = first;
        public boolean hasNext() {
            return ptr != last;
        }

        @Override
        public T next(){
            if (!hasNext()){
                throw new RuntimeException("Ring buffer underflow");
            }
            T returnitem = rb[ptr];
            ptr = moveRight(ptr);
            return returnitem;
        }
    }
//    public static void main(String[] args) {
//        BoundedQueue<Integer> A = new ArrayRingBuffer<>(5);
//        A.enqueue(1);
//        A.enqueue(2);
//        A.enqueue(3);
//        A.enqueue(4);
//        A.enqueue(5);
//        A.dequeue();
//        A.dequeue();
//        A.enqueue(5);
//
//        BoundedQueue<Integer> B = new ArrayRingBuffer<>(5);
//        B.enqueue(3);
//        B.enqueue(4);
//        B.enqueue(5);
//        B.enqueue(5);
//
//        System.out.println(A.equals(B));
//        for (Integer i : A){
//            System.out.println(i);
//        }
//        System.out.println();
//        for (Integer j : B){
//            System.out.println(j);
//        }
//    }
}
