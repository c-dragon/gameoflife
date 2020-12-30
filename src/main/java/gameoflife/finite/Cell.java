package gameoflife.finite;

public abstract class Cell {
    public static Cell fromInt(int i) {
        if (i == 1) {
            return new LivingCell();
        }
        return new DeadCell();
    }

    public abstract boolean isAlive();

    public abstract boolean livesInNextGeneration(int neighborsCount);

    public abstract int toInt();
}
