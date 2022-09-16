public class ArrayDeque <Type>{
    private int size;
    private int nextFirst;
    private Type[] items;
    private int nextLast;

    public ArrayDeque(){
        items = (Type[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int moveLeft(int pointer){
        if (pointer == 0){
            pointer = items.length - 1;
        }else{
            pointer -= 1;
        }
        return pointer;
    }

    private int moveRight(int pointer){
        if (pointer == items.length-1){
            pointer = 0;
        }else{
            pointer += 1;
        }
        return pointer;
    }

    private  boolean checkFull(){
        return (size == items.length);
    }

    private  void downSize(){
        if (size >= 16 && items.length/size > 4){
            resize(items.length/2);
        }
    }
    private void resize(int newsize){
        Type[] new_array = (Type[]) new Object[newsize];
        for (int i=0; i<size; i++){
            new_array[i] = items[nextLast];
            nextLast = moveRight(nextLast);
        }
        items = new_array;
        nextFirst = newsize-1;
        nextLast = size;
    }

    public void addFirst(Type item){
        if (checkFull()){
            resize(size *2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = moveLeft(nextFirst);
    }

    public void addLast(Type item){
        if (checkFull()){
            resize(size *2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = moveRight(nextLast);
    }

    public boolean isEmpty(){
        return (size==0);
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int ptrnextFirst = nextFirst;
        for (int i=0; i<size; i++){
            nextFirst = moveRight(nextFirst);
            System.out.print(items[nextFirst]+" ");
        }
        nextFirst = ptrnextFirst;
        System.out.println();
    }
    

    public Type removeFirst(){
        if (size==0){
            return null;
        }
        nextFirst = moveRight(nextFirst);
        Type value = items[nextFirst];
        items[nextFirst] = null;
        size = size - 1;
        downSize();
        return value;
    }

    public Type removeLast(){
        if (size==0){
            return null;
        }
        nextLast = moveLeft(nextLast);
        Type value = items[nextLast];
        items[nextLast] = null;
        size = size - 1;
        downSize();
        return value;
    }

    public Type get(int index){
        if (index >= size || index < 0){
            System.out.println("Invalid index!");
            return null;
        }
        return items[(moveRight(nextFirst) + index) % items.length];
    }

    public ArrayDeque(ArrayDeque other) {
        items = (Type[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;

        System.arraycopy(other.items, 0, items, 0, other.size);
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        System.out.println("Wheter empty: " + list.isEmpty());
        System.out.println("Size: " + list.size());
        for (int i = 20; i>10; i--){
            list.addFirst(i);
        }
        for (int i = 21; i<30; i++) {
            list.addLast(i);
        }
        System.out.println("Wheter empty: " + list.isEmpty());
        System.out.println("Size: " + list.size());
        list.printDeque();
        list.printDeque();
        System.out.println("First item removed: " + list.removeFirst());
        list.printDeque();
        System.out.println("Size: " + list.size());
        System.out.println("Last item removed: " + list.removeLast());
        list.printDeque();
        System.out.println("Size: " + list.size());
        System.out.println("list[15] = " + list.get(15));
    }
}
