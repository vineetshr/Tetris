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

    public void moveBlockRight() {
        if (!checkRight()) return;
        block.moveRight();
        repaint();
    }

    public void moveBlockLeft() {
        if (!checkLeft()) return;
        block.moveLeft();
        repaint();
    }

    public void dropBlock() {
        while (checkBottom()) {
            block.moveDown();
        }
        repaint();
    }

    public void rotateBlock() {
        block.rotate();
        repaint();
    }

    private boolean checkBottom() {
        if (block.getBottomEdge() == rows) {
            return false;
        }

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int col = 0; col < w; col++) {
            for (int row = h - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) break; // for ArrayIndexOutOfBoundsException when block is above game area
                    if (background[y][x] != null) return false;
                    break;
                }
             }
        }

        return true;
    }

    private boolean checkLeft() {
        if (block.getLeftEdge() == 0) {
            return false;
        }

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) break; // for ArrayIndexOutOfBoundsException when block is above game area
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }

        return true;
    }

    private boolean checkRight() {
        if (block.getRightEdge() == columns) {
            return false;
        }

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int row = 0; row < h; row++) {
            for (int col = w - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) break; // for ArrayIndexOutOfBoundsException when block is above game area
                    if (background[y][x] != null) return false;
                    break;
                }
            }
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