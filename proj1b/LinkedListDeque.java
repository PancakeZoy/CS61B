public class LinkedListDeque<T> implements Deque<T>{
    private class Node {
        public Node prev;
        public T item;
        public Node next;
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private Node sentinel;
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x){
        Node add_item =  new Node(sentinel, x, sentinel.next);
        sentinel.next = add_item;
        add_item.next.prev = add_item;
        size += 1;
    }

    @Override
    public void addLast(T x){
        Node add_item = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = add_item;
        sentinel.prev = add_item;
        size += 1;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        Node ptr = sentinel;
        while (ptr.next != sentinel){
            System.out.print(ptr.next.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        T toRemove = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    @Override
    public T removeLast(){
        T toRemove = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    @Override
    public T get(int index){
        Node toReturn = sentinel.next;
        for (int i = 0; i < index; i++){
            toReturn = toReturn.next;
        }
        return toReturn.item;
    }

    private T getRecursive(int index, Node p){
        if (index == 0){
            return p.item;
        }
        return getRecursive(index-1, p.next);
    }

    public T getRecursive(int index){
        return getRecursive(index, sentinel.next);
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i)); // (T) is cast, since T of other is unknown
        }

    }
}