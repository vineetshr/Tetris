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

    public GameArea(int columns) {

        this.setBounds(0, 0, GAMEWIDTH, GAMEHEIGHT); // width should be divisible by no of columns and height should be divisible by cellSize
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.lightGray);

        this.columns = columns;
        cellSize = this.getBounds().width / columns;
        this.rows = this.getBounds().height / cellSize;

        spawnBlock();
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

                    g.setColor(c);
                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawRect(x, y, cellSize, cellSize);
                }
            }
        }
    }

    public void spawnBlock() {
        block = new TetrisBlock(new int[][]{{1, 0}, {1, 0}, {1, 1}}, Color.blue);
    }

    public void moveBlockDown() {
        block.moveDown();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBlock(g);
    }
}