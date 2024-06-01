package tetris;

import tetrisblocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameArea extends JPanel {

    public static final int GAMEWIDTH = 200;
    public static final int GAMEHEIGHT = 300;
    private final int rows;
    private final int columns;
    private final int cellSize;
    private TetrisBlock block;
    private TetrisBlock[] blocks;
    private Color[][] background;

    public GameArea(int columns) {

        this.setBounds(0, 0, GAMEWIDTH, GAMEHEIGHT); // width should be divisible by no of columns and height should be divisible by cellSize
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.lightGray);

        this.columns = columns;
        cellSize = this.getBounds().width / columns;
        this.rows = this.getBounds().height / cellSize;

        background = new Color[rows][columns];

        blocks = new TetrisBlock[]{new IShape(), new JShape(), new LShape(), new OShape(), new SShape(), new TShape(), new ZShape()};
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

    public void moveBlockToBackground() {
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

    public boolean isOutOfBounds() {
        if (block.getY() < 0) {
            block = null;
            return true;
        }
        return false;
    }

    public int clearLines() {
        boolean lineFilled;
        int linesCleared = 0; // counts the number of lines cleared to update the score

        for (int r = rows - 1; r >= 0; r--) {
            lineFilled = true;
            for (int c = 0; c < columns; c++) {
                if (background[r][c] == null) {
                    lineFilled = false;
                    break;
                }
            }

            if (lineFilled) {
                linesCleared++; // update the score
                clearLine(r);
                shiftDown(r);
                clearLine(0); //for the 0th row, since shiftDown deals with rows > 1
                r++; // so it rechecks the line for a complete block again before checking the rows above

                repaint();
            }
        }

        return linesCleared;
    }

    private void clearLine(int r) {
        for (int i = 0; i < columns; i++) {
            background[r][i] = null;
        }
    }

    private void shiftDown(int r) {
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < columns; col++) {
                background[row][col] = background[row - 1][col];
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
        Random r = new Random();
        block = blocks[r.nextInt(blocks.length)];
        block.spawn(columns);
    }

    public boolean moveBlockDown() {
        if (!checkBottom()) {
            return false;
        }

        block.moveDown();
        repaint();
        return true;
    }

    public void moveBlockRight() {
        if (block == null) return;
        if (!checkRight()) return;
        block.moveRight();
        repaint();
    }

    public void moveBlockLeft() {
        if (block == null) return;
        if (!checkLeft()) return;
        block.moveLeft();
        repaint();
    }

    public void dropBlock() {
        if (block == null) return;
        while (checkBottom()) {
            block.moveDown();
        }
        repaint();
    }

    public void rotateBlock() {
        if (block == null) return;
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