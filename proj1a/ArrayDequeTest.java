public class ArrayDequeTest {
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkIndex(int index, int expected, int actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void addIsEmptySizeIndexTest() {
        System.out.println("Running add/isEmpty/size/get test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        passed = checkEmpty(false, ad1.isEmpty()) && passed;
        passed = checkSize(8, ad1.size()) && passed;

        passed = checkIndex(0, 1, ad1.get(0)) && passed;

        ad1.addLast(9);
        passed = checkSize(9, ad1.size()) && passed;
        passed = checkIndex(8, 9, ad1.get(8)) && passed;

        ad1.printDeque();

        printTestStatus(passed);
    }

    public static void addRemoveSizeTest() {
        System.out.println("Running add/remove/isEmpty test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(1);
        ad1.addLast(2);
        passed = checkSize(2, ad1.size()) && passed;

        ad1.removeLast();
        passed = checkSize(1, ad1.size()) && passed;

        ad1.removeFirst();
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeIndexTest();
        addRemoveSizeTest();
    }
}
