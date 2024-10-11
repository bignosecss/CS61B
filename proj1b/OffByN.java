public class OffByN implements CharacterComparator {
    private final int numberOffBy;
    OffByN(int N) {
        this.numberOffBy = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == this.numberOffBy;
    }
}
