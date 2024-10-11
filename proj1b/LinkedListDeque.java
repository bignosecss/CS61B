public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    /*
    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            this.addLast((T) other.get(i));
        }
    }
    */

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel && sentinel.prev == sentinel;
    }

    @Override
    public int size() {
        return Math.max(size, 0);
    }

    @Override
    public void printDeque() {
        Node n = sentinel.next;
        while (n != sentinel) {
            System.out.println(n.item + " ");
            n = n.next;
        }
        System.out.println("\n");
    }

    @Override
    public T removeFirst() {
        T removedItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removedItem;
    }

    @Override
    public T removeLast() {
        T removedItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        int i = 0;
        Node n = sentinel.next;
        while (i != index) {
            n = n.next;
            i += 1;
        }
        return n.item;
    }

    private T getRecursiveHelper(Node current, int targetIndex, int currentIndex) {
        if (currentIndex == targetIndex) {
            return current.item;
        }
        return getRecursiveHelper(current.next, targetIndex, currentIndex + 1);
    }
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index, 0);
    }
}
