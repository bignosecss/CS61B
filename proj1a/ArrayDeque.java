public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = Math.floorDiv(items.length, 2);
        nextLast = nextFirst + 1;
    }

    private void resize(int capacity) {
        Item[] i = (Item[]) new Object[capacity];
        // When it's full, the value of nextLast is the index of the first element
        System.arraycopy(items, nextLast, i, 0, size - nextLast);
        System.arraycopy(items, 0, i, size - nextLast, nextLast);
        items = i;
        nextFirst = items.length - 1;
        nextLast = size;
    }
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = nextFirst == 0 ? items.length - 1 : nextFirst - 1;
    }
    public void addLast(Item item) {
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
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int actualIndex = (nextFirst + 1 + i) % items.length;
            System.out.println(items[actualIndex]);
        }
    }

    public Item removeFirst() {
        Item removedItem = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst += 1;
        size -= 1;
        return removedItem;
    }

    public Item removeLast() {
        Item removedItem = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;
        return removedItem;
    }

    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (nextFirst + 1 + index) % items.length;
        return items[actualIndex];
    }
}
