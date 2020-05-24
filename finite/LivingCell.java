package gameoflife.finite;

public class LivingCell extends Cell {

    public static final String IMAGE = "X";

    public static String getImage() {
        return IMAGE;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public boolean livesInNextGeneration(int neighborsCount) {
        return neighborsCount == 2 || neighborsCount == 3;
    }

    @Override
    public String toString() {
        return IMAGE;
    }

    @Override
    public int toInt() { return 1; };
}
