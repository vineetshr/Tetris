package tetris;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    public static final int GAMEWIDTH = 200;
    public static final int GAMEHEIGHT = 300;
    private final int rows;
    private final int columns;
    private final int cellSize;
    private int[][] block = {{1, 0}, {1, 0}, {1, 1}};

    public GameArea(int columns) {
        this.setBounds(0, 0, GAMEWIDTH, GAMEHEIGHT); // width should be divisible by no of columns and height should be divisible by cellSize
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.lightGray);

        this.columns = columns;
        cellSize = this.getBounds().width / columns;
        this.rows = this.getBounds().height / cellSize;
    }

    private void drawBlock(Graphics g) {
        for (int row = 0; row < block.length; row++) {
            for (int col = 0; col < block[0].length; col++) {
                if (block[row][col] == 1) {
                    g.setColor(Color.red);
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBlock(g);

    }
}
