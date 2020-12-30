package gameoflife.infinite;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cell {

    private static final int DIMENSION = 2;
    private int[] coordinate = new int[DIMENSION];

    static List<int[]> neighborDirections() {
        return Arrays.asList(
                new int[] { 1, 0 },
                new int[] { 1, 1 },
                new int[] { 0, 1 },
                new int[] { -1, 1 },
                new int[] { -1, 0 },
                new int[] { -1, -1 },
                new int[] { 0, -1 },
                new int[] { 1, -1 });
    }

    public static Cell createCellAtOrigin() {
        return new Cell(new int[]{0, 0});
    }

    public Cell(int[] coordinate) {
        assert(coordinate.length == DIMENSION);
        this.coordinate = Arrays.copyOf(coordinate, DIMENSION);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Cell cell = (Cell) object;

        return Arrays.equals(this.coordinate, cell.coordinate);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinate);
    }

    public List<Cell> neighbors() {
        return neighborDirections().stream().map(this::createNeighbor).collect(Collectors.toList());
    }

    public Cell createNeighbor(int[] distance) {
        assert(distance.length == DIMENSION);
        int[] neighborCoordinate = IntStream
                .range(0, distance.length)
                .map(i -> coordinate[i] + distance[i])
                .toArray();
        return new Cell(neighborCoordinate);
    }
}
