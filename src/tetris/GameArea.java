package tetris;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    private final int rows;
    private final int columns;
    private int cellSize;

    public GameArea(int columns) {
        this.setBounds(0, 0, 200, 200); // width should be divisible by no of columns and height should be divisible by cellSize
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.lightGray);


        this.columns = columns;
        cellSize = this.getBounds().height / columns;
        this.rows = this.getBounds().height / cellSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int x = 0; x < cellSize; x++) {
            g.drawRect(x * cellSize, 0, cellSize, cellSize);
        }
    }

}
