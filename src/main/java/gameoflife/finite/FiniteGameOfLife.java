package gameoflife.finite;

import gameoflife.GameOfLife;

public class FiniteGameOfLife implements GameOfLife {
    private final int dimension;
    Cell[][] cells;

    public FiniteGameOfLife(int dimension) {
        this.dimension = dimension;
        cells = new Cell[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int k = 0; k < dimension; k++) {
                cells[i][k] = new DeadCell();
            }
        }
    }

    public FiniteGameOfLife(int[][] data) {
        this.dimension = data[0].length;
        cells = new Cell[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int k = 0; k < dimension; k++) {
                cells[i][k] = Cell.fromInt(data[i][k]);
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        String image = "";
        for (int i = 0; i < dimension; i++) {
            for (int k = 0; k < dimension; k++) {
                image += cells[i][k] + " ";
            }
            image += "\n";
        }
        return image;
    }

    public void toggleCell(int line, int column) {
        if (isAlive(line, column)) {
            cells[line][column] = new DeadCell();
        } else {
            cells[line][column] = new LivingCell();
        }
    }

    public FiniteGameOfLife nextGeneration() {
        FiniteGameOfLife nextWorld = new FiniteGameOfLife(dimension);
        for (int i = 0; i < dimension; i++) {
            for (int k = 0; k < dimension; k++) {
                if (livesInNextGeneration(i, k)) {
                    nextWorld.toggleCell(i, k);
                }
            }
        }
        return nextWorld;
    }

    private boolean livesInNextGeneration(int i, int k) {
        return  cells[i][k].livesInNextGeneration(neighborsCount(i, k));
    }

    private int neighborsCount(int line, int column) {
        int neighborsCount = 0;
        for (int i = line - 1; i <= line + 1; i++) {
            for (int k = column - 1; k <= column + 1; k++) {
                if (i < 0 || k < 0 || i >= dimension || k >= dimension) continue;
                if (i == line && k == column) continue;
                if (cells[i][k].isAlive()) {
                    neighborsCount++;
                }
            }
        }
        return neighborsCount;
    }

    public void set(String worldImage) {
        String image = removeWhitespace(worldImage);
        for (int i = 0; i < dimension; i++) {
            for (int k = 0; k < dimension; k++) {
                cells[i][k] = CellFactory.fromString(imageAt(image, i, k));
            }
        }
    }

    private String removeWhitespace(String worldImage) {
        return worldImage.replaceAll("\\s", "");
    }

    private String imageAt(String image, int i, int k) {
        return image.substring(i * dimension + k, i * dimension + k + 1);
    }

    public int[][] getData() {
        int[][] data = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int k = 0; k < dimension; k++) {
                data[i][k] = cells[i][k].toInt();
            }
        }
        return data;
    }

    public boolean isAlive(int i, int k) {
        return cells[i][k].isAlive();
    }
}
