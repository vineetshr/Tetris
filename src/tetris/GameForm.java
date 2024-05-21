package tetris;

import javax.swing.*;

public class GameForm extends JFrame {


    private GameArea ga;
    public GameForm() {
        ga = new GameArea(10);
        this.add(ga);

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
