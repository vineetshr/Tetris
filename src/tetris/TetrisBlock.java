package tetris;

import java.awt.*;
import java.util.Random;

public class TetrisBlock {

    private int[][] shape;
    private Color color;
    private int x, y;
    private int[][][] shapes;
    private int currentRotation;
    private Color[] availableColors = {Color.green, Color.red, Color.blue};

    public TetrisBlock(int[][] shape) {
        this.shape = shape;

        initShapes();
    }

    private void initShapes() {
        shapes = new int[4][][];

        for (int i = 0; i < 4; i++) {
            int c = shape.length;
            int r = shape[0].length;
            shapes[i] = new int[r][c];

            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    shapes[i][y][x] = shape[c - x - 1][y];
                }
            }

            shape = shapes[i];
        }
    }

    public void spawn(int gridWidth) {
        Random r = new Random();

        currentRotation = r.nextInt(shape.length);
        shape = shapes[currentRotation]; // init before y and x coordinates, as it depends on the tetris block

        y = -getHeight(); // block appears above the game area
        x = r.nextInt(gridWidth - getWidth()); // block spawns randomly

        color = availableColors[r.nextInt(availableColors.length)];
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        y = newY;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void rotate() {
        currentRotation++;
        if (currentRotation > 3) currentRotation = 0;
        shape = shapes[currentRotation];
    }

    public int getBottomEdge() {
        return y + getHeight();
    }

    public int getLeftEdge() {
        return x; // having 2 methods return the same value improves code readability
    }

    public int getRightEdge() {
        return x + getWidth();
    }
}
