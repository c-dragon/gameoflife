package gameoflife.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLifeActionListener implements ActionListener {

    GameOfLifeController contoller;

    public GameOfLifeActionListener(GameOfLifeController controller) {
        this.contoller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if (actionCommand.equals("Start")) {
            contoller.setGameRunning(true);
        } else if (actionCommand.equals("Stop")) {
            contoller.setGameRunning(false);
        } else if (actionCommand.equals("Step")) {
            contoller.nextGeneration();
        }
    }
}