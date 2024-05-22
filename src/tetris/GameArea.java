package tetris;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    public static final int GAMEWIDTH = 200;
    public static final int GAMEHEIGHT = 300;
    private final int rows;
    private final int columns;
    private final int cellSize;
    private TetrisBlock block;
    private Color[][] background;

    public GameArea(int columns) {

        this.setBounds(0, 0, GAMEWIDTH, GAMEHEIGHT); // width should be divisible by no of columns and height should be divisible by cellSize
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.lightGray);

        this.columns = columns;
        cellSize = this.getBounds().width / columns;
        this.rows = this.getBounds().height / cellSize;

        background = new Color[rows][columns];
    }

    private void drawBlock(Graphics g) {

        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {

                    int x = (block.getX() + col) * cellSize;
                    int y = (block.getY() + row) * cellSize;

                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }

    private void drawBackground(Graphics g) {

        Color color;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                color = background[r][c];

                if (color != null) {
                    int x = c * cellSize;
                    int y = r * cellSize;

                    drawGridSquare(g, color, x, y);

                }
            }
        }
    }

    private void moveBlockToBackground() {
        int h = block.getHeight();
        int w = block.getWidth();
        Color color = block.getColor();
        int[][] shape = block.getShape();
        int xPos = block.getX();
        int yPos = block.getY();

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {
                    background[row + yPos][col + xPos] = color;
                }
            }
        }
    }

    private void drawGridSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, cellSize, cellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, cellSize, cellSize);
    }

    public void spawnBlock() {
        block = new TetrisBlock(new int[][]{{1, 0}, {1, 0}, {1, 1}}, Color.blue);
        block.spawn(columns);
    }

    public boolean moveBlockDown() {
        if (!checkBottom()) {
            moveBlockToBackground();
            return false;
        }

        block.moveDown();
        repaint();
        return true;
    }

    private boolean checkBottom() {
        if (block.getBottomEdge() == rows) {
            return false;
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, GAMEWIDTH, GAMEHEIGHT);
        drawBackground(g);
        drawBlock(g);
    }
}