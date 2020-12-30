package gameoflife.infinite;

import gameoflife.infinite.Cell;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CellTest {

    @Test
    public void allEightDirectionsLeadToNeighbors() {
        List<int[]> expectedNeighborDirections = Arrays.asList(
                new int[] { 1, 0 },
                new int[] { 1, 1 },
                new int[] { 0, 1 },
                new int[] { -1, 1 },
                new int[] { -1, 0 },
                new int[] { -1, -1 },
                new int[] { 0, -1 },
                new int[] { 1, -1 });

        List<int[]> neighborDirections = Cell.neighborDirections();

        assertEquals(expectedNeighborDirections.size(), neighborDirections.size());
        for (int i = 0; i < expectedNeighborDirections.size(); i++) {
            assertTrue(Arrays.equals(expectedNeighborDirections.get(i), neighborDirections.get(i)));
        }
    }

    @Test
    public void createNeighborCreatesCellInGivenDistance() {
        Cell cell = Cell.createCellAtOrigin();
        int[] distance = new int[]{1, 1};
        Cell expectedNeighbor = new Cell(distance);

        Cell neighbor = cell.createNeighbor(distance);

        assertEquals(expectedNeighbor, neighbor);
    }

    @Test
    public void allCellsAroundACellAreNeighbors() {
        Cell cell = Cell.createCellAtOrigin();

        List<Cell> expectedNeighbors = Cell.neighborDirections().stream().map(coordinate -> new Cell(coordinate))
                .collect(Collectors.toList());

        List<Cell> neighbors = cell.neighbors();
        assertEquals(expectedNeighbors.size(), neighbors.size());
        assertTrue(neighbors.containsAll(expectedNeighbors));
    }
}