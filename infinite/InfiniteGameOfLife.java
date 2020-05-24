package gameoflife.infinite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InfiniteGameOfLife {

    private List<Cell> livingCells = new ArrayList<>();

    public void iterate() {
        resetLivingCellsBy(survivingCells(), newbornCells());
    }

    private void resetLivingCellsBy(List<Cell> survivingCells, List<Cell> newbornCells) {
        livingCells.clear();
        livingCells.addAll(survivingCells);
        livingCells.addAll(newbornCells);
    }

    private List<Cell> newbornCells() {
        return livingCells.stream()
                .map(Cell::neighbors)
                .flatMap(Collection::stream)
                .distinct()
                .filter(this::isDead)
                .filter(this::isNewborn)
                .collect(Collectors.toList());
    }

    private boolean isDead(Cell cell) {
        return !livingCells.contains(cell);
    }

    private List<Cell> survivingCells() {
        return livingCells.stream().filter(this::isSurviving).collect(Collectors.toList());
    }

    private boolean isNewborn(Cell cell) {
        long neighborCount = livingNeighborCount(cell);
        return neighborCount == 3;
    }

    private boolean isSurviving(Cell cell) {
        long neighborCount = livingNeighborCount(cell);
        return neighborCount == 2 || neighborCount == 3;
    }

    public long livingNeighborCount(Cell cell) {
        List<Cell> neighbors = cell.neighbors();
        return neighbors.stream().filter(neighbor -> livingCells.contains(neighbor)).count();
    }

    public List<Cell> livingCells() {
        return livingCells;
    }

    public void setAlive(Cell cell) {
        livingCells.add(cell);
    }
}