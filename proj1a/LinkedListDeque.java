public class LinkedListDeque<Type> {
    private class Node {
        public Node prev;
        public Type item;
        public Node next;
        public Node(Node p, Type i, Node n) {
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

    public void addFirst(Type x){
        Node add_item =  new Node(sentinel, x, sentinel.next);
        sentinel.next = add_item;
        add_item.next.prev = add_item;
        size += 1;
    }

    public void addLast(Type x){
        Node add_item = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = add_item;
        sentinel.prev = add_item;
        size += 1;
    }

    public boolean isEmpty(){
        return (size==0);
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node ptr = sentinel;
        while (ptr.next != sentinel){
            System.out.print(ptr.next.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public Type removeFirst(){
        Type toRemove = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    public Type removeLast(){
        Type toRemove = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    public Type get(int index){
        Node toReturn = sentinel.next;
        for (int i = 0; i < index; i++){
            toReturn = toReturn.next;
        }
        return toReturn.item;
    }

    private Type getRecursive(int index, Node p){
        if (index == 0){
            return p.item;
        }
        return getRecursive(index-1, p.next);
    }

    public Type getRecursive(int index){
        return getRecursive(index, sentinel.next);
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((Type) other.get(i)); // (Type) is cast, since type of other is unknown
        }

    }


    public static void main(String[] args) {
        LinkedListDeque<String> list = new LinkedListDeque<>();
        System.out.println("Wheter empty: " + list.isEmpty());
        System.out.println("Size: " + list.size());
        list.addFirst("cat");
        list.addFirst("dog");
        list.addLast("frog");
        list.addLast("milf");
        list.addLast("horse");
        list.addLast("pancake");
        System.out.println("Wheter empty: " + list.isEmpty());
        System.out.println("Size: " + list.size());
        list.printDeque();
        System.out.println("First item removed: " + list.removeFirst());
        list.printDeque();
        System.out.println("Size: " + list.size());
        System.out.println("Last item removed: " + list.removeLast());
        list.printDeque();
        System.out.println("Size: " + list.size());
        System.out.println("list[1] = " + list.get(1));
        System.out.println("list[1] = " + list.getRecursive(1));

    }
}