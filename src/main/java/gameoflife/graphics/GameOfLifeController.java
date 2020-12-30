package gameoflife.graphics;

import gameoflife.GameOfLife;
import gameoflife.finite.FiniteGameOfLife;

public class GameOfLifeController {

    private boolean gameRunning;
    private final GameOfLifeFrame gameOfLifeFrame;

    public static void main(String[] args) throws InterruptedException {
        GameOfLife gameOfLife = createGliderGame();
        GameOfLifeController controller = new GameOfLifeController(gameOfLife);
        controller.run();
    }

    public GameOfLifeController(GameOfLife gameOfLife) {
        gameRunning = false;
        GameOfLifeActionListener actionListener = new GameOfLifeActionListener(this);
        gameOfLifeFrame = new GameOfLifeFrame(gameOfLife, actionListener);
    }

    public void run() throws InterruptedException {
        while (true) {
            Thread.sleep(200);
            if (gameRunning) {
                nextGeneration();
            }
        }
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public void nextGeneration() {
        GameOfLife gameOfLife = gameOfLifeFrame.getGameOfLife();
        gameOfLifeFrame.setGameOfLife(gameOfLife.nextGeneration());
    }

    public static GameOfLife createGliderGame() {
        GameOfLife gameOfLife = new FiniteGameOfLife(40);
        gameOfLife.toggleCell(0, 1);
        gameOfLife.toggleCell(1, 2);
        gameOfLife.toggleCell(2, 0);
        gameOfLife.toggleCell(2, 1);
        gameOfLife.toggleCell(2, 2);
        return gameOfLife;
    }
}