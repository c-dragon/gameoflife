package gameoflife.finite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FiniteInfiniteGameOfLifeTest {
    public static final int DIMENSION = 3;
    private FiniteGameOfLife world;
    // world with one living cell is empty after one iteration

    @BeforeEach
    void setup() {
        world = new FiniteGameOfLife(DIMENSION);
    }

    @Test
    void new_world_is_empty() {
        String expected =
                ". . . \n" +
                        ". . . \n" +
                        ". . . \n";
        assertEquals(expected, world.toString());
    }


    @Test
    void string_representation_shows_dead_cells_and_living_cells() {
        world.toggleCell(1, 2);
        String expected =
                ". . . \n" +
                        ". . X \n" +
                        ". . . \n";
        assertEquals(expected, world.toString());
    }

    @Test
    void world_can_be_set_from_image_of_dead_and_living_cells() {
        String initialWorld =
                ". . . \n" +
                        ". . X \n" +
                        ". . . \n";
        world.set(initialWorld);
        assertEquals(initialWorld, world.toString());
    }

    @Test
    void toggle_inverts_state_of_cell() {
        String initialWorld =
                ". . . \n" +
                        ". . X \n" +
                        ". . . \n";
        world.set(initialWorld);
        world.toggleCell(0, 0);
        world.toggleCell(1, 2);
        String expected =
                "X . . \n" +
                        ". . . \n" +
                        ". . . \n";
        assertEquals(expected, world.toString());
    }

    @Test
    void world_with_one_living_cell_is_empty_after_one_iteration() {
        world.toggleCell(1, 1);
        String expected =
                ". . . \n" +
                        ". . . \n" +
                        ". . . \n";
        assertEquals(expected, world.nextGeneration().toString());
    }

    @Test
    void living_cell_with_two_neighbors_survives() {
        String initialWorld =
                "X . . \n" +
                        ". X . \n" +
                        ". . X \n";
        world.set(initialWorld);
        String expected =
                ". . . \n" +
                        ". X . \n" +
                        ". . . \n";
        assertEquals(expected, world.nextGeneration().toString());
    }

    @Test
    void living_cell_with_three_neighbors_survives() {
        String initialWorld =
                "X . . \n" +
                        ". X . \n" +
                        "X . X \n";
        world.set(initialWorld);
        String expected =
                ". . . \n" +
                        "X X . \n" +
                        ". X . \n";
        assertEquals(expected, world.nextGeneration().toString());
    }

    @Test
    void dead_cell_with_three_neighbors_is_born() {
        String initialWorld =
                "X . . \n" +
                        ". . . \n" +
                        "X . X \n";
        world.set(initialWorld);
        String expected =
                ". . . \n" +
                        ". X . \n" +
                        ". . . \n";
        assertEquals(expected, world.nextGeneration().toString());
    }

    @Test
    void living_cell_with_four_neighbors_dies() {
        String initialWorld =
                "X . X \n" +
                        ". . . \n" +
                        "X . X \n";
        world.set(initialWorld);
        String expected =
                ". . . \n" +
                        ". . . \n" +
                        ". . . \n";
        assertEquals(expected, world.nextGeneration().toString());
    }

    @Test
    void data_is_0_for_dead_cells_and_1_for_living_cells() {
        String initialWorld =
                "X X . \n" +
                ". . . \n" +
                "X . X \n";
        world.set(initialWorld);
        int[][] expected = { {1, 1, 0}, {0, 0, 0}, {1, 0, 1}};
        assertArrayEquals(expected, world.getData());
    }

    @Test
    void game_can_be_created_from_data() {
        int[][] data = { {1, 1, 0}, {0, 0, 0}, {1, 0, 1}};
        FiniteGameOfLife game = new FiniteGameOfLife(data);
        assertArrayEquals(data, game.getData());
    }
}
