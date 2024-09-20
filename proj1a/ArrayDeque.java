public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = Math.floorDiv(items.length, 2);
        nextLast = nextFirst + 1;
    }

    private void resize(int capacity) {
        T[] i = (T[]) new Object[capacity];
        // When it's full, the value of nextLast is the index of the first element
        System.arraycopy(items, nextLast, i, 0, size - nextLast);
        System.arraycopy(items, 0, i, size - nextLast, nextLast);
        items = i;
        nextFirst = items.length - 1;
        nextLast = size;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = nextFirst == 0 ? items.length - 1 : nextFirst - 1;
    }
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = nextLast == items.length - 1 ? 0 : nextLast + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return Math.max(size, 0);
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int actualIndex = (nextFirst + 1 + i) % items.length;
            System.out.println(items[actualIndex]);
        }
    }

    private void halve() {
        double R = (double) size / items.length;
        if (items.length > 16 && R <= 0.25) {
            resize(items.length / 2);
        }
    }
    public T removeFirst() {
        T removedItem = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst += 1;
        size -= 1;
        halve();
        return removedItem;
    }
    public T removeLast() {
        T removedItem = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;
        halve();
        return removedItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (nextFirst + 1 + index) % items.length;
        return items[actualIndex];
    }
}
