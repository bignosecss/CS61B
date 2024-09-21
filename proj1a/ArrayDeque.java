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
        T[] tmp = (T[]) new Object[capacity];
        int startOfDeque = nextFirst == items.length - 1 ? 0 : nextFirst + 1;
        int endOfDeque = nextLast == 0 ? items.length - 1 : nextLast - 1;
        if (startOfDeque < endOfDeque) {
            System.arraycopy(items, startOfDeque, tmp, 0, size);
        } else {
            System.arraycopy(items, startOfDeque, tmp, 0, items.length - startOfDeque);
            System.arraycopy(items, 0, tmp, items.length - startOfDeque, endOfDeque + 1);
        }
        items = tmp;
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
        if (isEmpty()) {
            return null;
        }
        int indexOfFirstItem = nextFirst == items.length - 1 ? 0 : nextFirst + 1;
        T removedItem = items[indexOfFirstItem];
        items[indexOfFirstItem] = null;
        nextFirst = indexOfFirstItem;
        size -= 1;
        halve();
        return removedItem;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int indexOfLastItem = nextLast == 0 ? items.length - 1 : nextLast - 1;
        T removedItem = items[indexOfLastItem];
        items[indexOfLastItem] = null;
        nextLast = indexOfLastItem;
        size -= 1;
        halve();
        return removedItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int indexOfFirstItem = nextFirst == items.length - 1 ? 0 : nextFirst + 1;
        int actualIndex = (indexOfFirstItem + index) % items.length;
        return items[actualIndex];
    }
}
