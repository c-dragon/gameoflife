package gameoflife.finite;

public class DeadCell extends Cell {

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public boolean livesInNextGeneration(int neighborsCount) {
        return neighborsCount == 3;
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public int toInt() { return 0; };
}
