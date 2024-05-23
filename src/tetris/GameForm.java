package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameForm extends JFrame {

    private GameArea gameArea;

    public GameForm() {
        gameArea = new GameArea(10);
        this.add(gameArea);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);

        startGame();
        initControls();
    }

    private void initControls() {
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("right");

            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left");

            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("up");

            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("down");

            }
        });
    }

    public void startGame() {
        new GameThread(gameArea).start();
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
