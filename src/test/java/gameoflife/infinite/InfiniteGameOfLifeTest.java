package gameoflife.infinite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InfiniteGameOfLifeTest {

    @Test
    public void gameWithOnlyDeadCellsHasOnlyDeadCellsAfterOneIteration() {
        InfiniteGameOfLife game = new InfiniteGameOfLife();

        game.iterate();

        Assertions.assertEquals(0, game.livingCells().size());
    }

    @Test
    public void gameWithOneLivingCellHasOnlyDeadCellsAfterOneIteration() {
        InfiniteGameOfLife game = new InfiniteGameOfLife();
        Cell cell = Cell.createCellAtOrigin();
        game.setAlive(cell);

        game.iterate();

        Assertions.assertEquals(0, game.livingCells().size());

    }

    @Test
    public void livingCellWithTwoLivingNeighborsSurvivesOneIteration() {
        InfiniteGameOfLife game = new InfiniteGameOfLife();
        Cell testCell = Cell.createCellAtOrigin();
        game.setAlive(testCell);
        game.setAlive(new Cell(new int[] {1,0}));
        game.setAlive(new Cell(new int[] {-1, 0}));

        game.iterate();

        Assertions.assertTrue(game.livingCells().contains(testCell));
    }

    @Test
    public void deadCellWithTwoLivingNeighborsIsDeadAfterOneIteration() {
        InfiniteGameOfLife game = new InfiniteGameOfLife();
        Cell testCell = Cell.createCellAtOrigin();
        game.setAlive(new Cell(new int[] {1,0}));
        game.setAlive(new Cell(new int[] {-1, 0}));

        game.iterate();

        Assertions.assertFalse(game.livingCells().contains(testCell));
    }

    @Test
    public void livingCellWithThreeLivingNeighborsSurvivesOneIteration() {
        InfiniteGameOfLife game = new InfiniteGameOfLife();
        Cell testCell = Cell.createCellAtOrigin();
        game.setAlive(testCell);
        game.setAlive(new Cell(new int[] {1,0}));
        game.setAlive(new Cell(new int[] {-1, 0}));
        game.setAlive(new Cell(new int[] {0, 1}));

        game.iterate();

        Assertions.assertTrue(game.livingCells().contains(testCell));
    }

    @Test
    public void deadCellWithThreeLivingNeighborsLivesAfterOneIteration() {
        InfiniteGameOfLife game = new InfiniteGameOfLife();
        Cell testCell = Cell.createCellAtOrigin();
        game.setAlive(new Cell(new int[] {1,0}));
        game.setAlive(new Cell(new int[] {-1, 0}));
        game.setAlive(new Cell(new int[] {0, 1}));

        game.iterate();

        Assertions.assertTrue(game.livingCells().contains(testCell));
    }
}
