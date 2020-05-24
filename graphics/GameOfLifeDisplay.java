package gameoflife.graphics;

import gameoflife.GameOfLife;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GameOfLifeDisplay extends Component implements MouseListener {

    public static final int CELL_WIDTH = 20;
    public static final int CELL_HEIGHT = 20;
    private static final int BORDER_X = 50;
    private static final int BORDER_Y = 30;

    GameOfLife gameOfLife;

    public GameOfLifeDisplay(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        addMouseListener(this);
    }

    public GameOfLife getGameOfLife() {
        return gameOfLife;
    }

    public void setGameOfLife(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(
                BORDER_X + gameOfLife.getDimension() * CELL_WIDTH + BORDER_X,
                BORDER_Y + gameOfLife.getDimension() * CELL_HEIGHT + BORDER_Y);
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < gameOfLife.getDimension(); i++) {
            for (int k = 0; k < gameOfLife.getDimension(); k++) {
                drawCell(graphics, i, k);
            }
        }
    }

    private void drawCell(Graphics graphics, int i, int k) {
        if (gameOfLife.isAlive(i, k)) {
            drawLivingCell(graphics, i, k);
        } else {
            drawDeadCell(graphics, i, k);
        }
    }

    private void drawDeadCell(Graphics graphics, int i, int k) {
        graphics.drawRect(BORDER_X + i * CELL_WIDTH, BORDER_Y + k * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
    }

    private void drawLivingCell(Graphics graphics, int i, int k) {
        drawDeadCell(graphics, i, k);
        graphics.fillRect(BORDER_X + i * CELL_WIDTH, BORDER_Y + k * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int line = getLineAtMousePosition(event);
        int column = getColumnAtMousePosition(event);
        if (line >= 0 && line < gameOfLife.getDimension() &&
                column >= 0 && column < gameOfLife.getDimension()) {
            gameOfLife.toggleCell(line, column);
            repaint();
        }
    }

    private int getLineAtMousePosition(MouseEvent event) {
        Point screenOffset = getLocationOnScreen();
        Point mousePosition = event.getLocationOnScreen();
        return (mousePosition.x - BORDER_X - screenOffset.x) / CELL_WIDTH;
    }

    private int getColumnAtMousePosition(MouseEvent event) {
        Point screenOffset = getLocationOnScreen();
        Point mousePosition = event.getLocationOnScreen();
        return (mousePosition.y - BORDER_Y - screenOffset.y) / CELL_HEIGHT;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // nothing to do
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // nothing to do
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // nothing to do
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // nothing to do
    }
}
