package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameForm extends JFrame {

    private GameArea gameArea;
    private JPanel panel1;
    private JLabel scoreLabel;
    private JLabel levelLabel;

    public GameForm() {
        gameArea = new GameArea(10);
        this.add(gameArea);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);

        scoreLabel = new JLabel("Score: ");
        levelLabel = new JLabel("Level: 1");
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        scoreLabel.setFont(labelFont);
        levelLabel.setFont(labelFont);
        gameArea.add(scoreLabel);
        gameArea.add(levelLabel);

        initControls();
        startGame();

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
                gameArea.moveBlockRight();
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockLeft();
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotateBlock();
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.dropBlock();
            }
        });
    }

    public void startGame() {
        new GameThread(gameArea, this).start();
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: "  + score);
    }

    public void updateLevel(int level) {
        levelLabel.setText("Level: " + level);
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
