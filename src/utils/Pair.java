package utils;

public class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair add(Pair p) {
        return new Pair(x + p.x, y + p.y);
    }
}
