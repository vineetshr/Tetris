import javax.swing.JPanel;
import java.awt.Graphics;

public class GameArea extends JPanel {

    public GameArea() {
        this.setBounds(0, 0, 100, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0, 0, 50, 50);
    }

}
