package tetris;

import javax.swing.*;
import java.awt.*;

public class GameForm extends JFrame {

    private GameArea gameArea;

    public GameForm() {
        gameArea = new GameArea(10);
        this.add(gameArea);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameForm().setVisible(true);

            }
        });

    }
}
