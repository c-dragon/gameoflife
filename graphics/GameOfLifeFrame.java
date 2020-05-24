package gameoflife.graphics;

import gameoflife.GameOfLife;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameOfLifeFrame {

    JFrame frame;
    private GameOfLifeDisplay gameOfLifeDisplay;
    private ActionListener actionListener;

    public GameOfLifeFrame(GameOfLife gameOfLife, ActionListener actionListener) {
        this.actionListener = actionListener;
        createGameOfLifeFrame(gameOfLife);
    }

    private void createGameOfLifeFrame(GameOfLife gameOfLife) {
        frame = new JFrame("Game of Life");
        frame.addWindowListener(new WindowAdapter() {
                                    public void windowClosing(WindowEvent event) {
                                        frame.dispose();
                                    }
                                }
        );
        frame.setLayout(new BorderLayout());
        initComponents(gameOfLife);
        frame.pack();
        frame.setVisible(true);
    }

    public void initComponents(GameOfLife gameOfLife) {
        addButtonPanel(BorderLayout.NORTH);
        addGameOfLifePainter(gameOfLife, BorderLayout.CENTER);
    }

    private void addGameOfLifePainter(GameOfLife gameOfLife, String layoutDirection) {
        gameOfLifeDisplay = new GameOfLifeDisplay(gameOfLife);
        frame.add(layoutDirection, gameOfLifeDisplay);
    }

    private void addButtonPanel(String layoutDirection) {
        Panel buttonPanel = createFlowLayoutPanel();
        addButtonToPanel(buttonPanel, "Start");
        addButtonToPanel(buttonPanel, "Stop");
        addButtonToPanel(buttonPanel, "Step");
        frame.add(layoutDirection, buttonPanel);
    }

    private Button addButtonToPanel(Panel panel, String title) {
        Button button = new Button();
        button.setLabel(title);
        button.addActionListener(actionListener);
        panel.add(button);
        return button;
    }

    private Panel createFlowLayoutPanel() {
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        return panel;
    }

    public GameOfLife getGameOfLife() {
        return gameOfLifeDisplay.getGameOfLife();
    }

    public void setGameOfLife(GameOfLife gameOfLife) {
        gameOfLifeDisplay.setGameOfLife(gameOfLife);
    }

    private TextField addTextFieldWithLabelToPanel(Panel panel, String labelText) {
        Label label = new Label();
        label.setText(labelText);
        panel.add(label);

        TextField field = new TextField(2);
        field.setText("0");
        panel.add(field);
        return field;
    }
}