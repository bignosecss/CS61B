public class LinkedListDeque<T> {
    public class Node {
        public Node prev;
        public T item;
        public Node next;

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
    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    // public LinkedListDeque(LinkedListDeque other) {}

    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return sentinel.next == sentinel && sentinel.prev == sentinel;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node n = sentinel.next;
        while (n != sentinel) {
            System.out.println(n.item + " ");
            n = n.next;
        }
        System.out.println("\n");
    }

    public T removeFirst() {
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        return removedItem;
    }

    public T removeLast() {
        T removedItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return removedItem;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int i = 0;
        Node n = sentinel;
        while (i != index) {
            n = n.next;
            i += 1;
        }
        return n.item;
    }

    public T getRecursive(int index) {
        if (index == 0) {
            return sentinel.next.item;
        }
        return getRecursive(index - 1);
    }
}
