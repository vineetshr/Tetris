import javax.swing.JFrame;

public class GameForm extends JFrame {
    public GameForm()  {

        this.add(new GameArea());

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
