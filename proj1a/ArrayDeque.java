public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private T[] items;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int moveLeft(int pointer){
        return (pointer - 1 + items.length) % items.length;
    }

    private int moveRight(int pointer){
        return (pointer + 1) % items.length;
    }

    private boolean checkFull(){
        return (size == items.length);
    }

    private void downSize(){
        if (size >= 16 && items.length/size > 4){
            resize(items.length/2);
        }
    }

    private void resize(int newsize){
        T[] new_array = (T[]) new Object[newsize];
        for (int i=0; i<size; i++){
            nextFirst = moveRight(nextFirst);
            new_array[i] = items[nextFirst];
        }
        items = new_array;
        nextFirst = newsize-1;
        nextLast = size;
    }

    public void addFirst(T item){
        if (checkFull()){
            resize(size *2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = moveLeft(nextFirst);
    }

    public void addLast(T item){
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


    public T removeFirst(){
        nextFirst = moveRight(nextFirst);
        T value = items[nextFirst];
        items[nextFirst] = null;
        if (size != 0){
            size = size - 1;
        }
        downSize();
        return value;
    }

    public T removeLast(){
        nextLast = moveLeft(nextLast);
        T value = items[nextLast];
        items[nextLast] = null;
        if (size!=0){
            size = size - 1;
        }
        downSize();
        return value;
    }

    public T get(int index){
        if (index >= size || index < 0){
            return null;
        }
        return items[(moveRight(nextFirst) + index) % items.length];
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;

        System.arraycopy(other.items, 0, items, 0, other.size);
    }
}
