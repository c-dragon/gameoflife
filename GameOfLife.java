package gameoflife;

public interface GameOfLife {
    int getDimension();
    void toggleCell(int line, int column);
    boolean isAlive(int line, int column);
    GameOfLife nextGeneration();
}
