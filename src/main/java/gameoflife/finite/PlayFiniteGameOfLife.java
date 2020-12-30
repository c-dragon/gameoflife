package gameoflife.finite;

public class PlayFiniteGameOfLife {
    // next time maybe with https://docs.oracle.com/javase/tutorial/2d/basic2d/index.html
    public static void main(String[] args) {
        playBarGame();
        playGliderGame();
    }

    private static void playBarGame() {
        FiniteGameOfLife game = new FiniteGameOfLife(5);
        String initialWorld =
                "0 0 0 0 0 \n" +
                "0 0 X 0 0 \n" +
                "0 0 X 0 0 \n" +
                "0 0 X 0 0 \n" +
                "0 0 0 0 0 \n";
        game.set(initialWorld);
        play(game, 10);
    }

    private static void playGliderGame() {
        FiniteGameOfLife game = new FiniteGameOfLife(6);
        String initialWorld =
                "0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 \n" +
                "0 0 0 X X X \n" +
                "0 0 0 X 0 0 \n" +
                "0 0 0 0 X 0 \n";
        game.set(initialWorld);
        play(game, 10);
    }

    private static void play(FiniteGameOfLife game, int generations) {
        for (int i = 0; i < generations; i++) {
            game = game.nextGeneration();
            System.out.println(game);
        }
    }
}
