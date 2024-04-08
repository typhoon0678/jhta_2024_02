package Practice0B.graphics;

import javax.swing.*;
import java.awt.*;

class MyPanel03 extends JPanel {
    private ImageIcon imageIcon = new ImageIcon("src/Practice0B/graphics/images/spaceship.png");
    private Image image = imageIcon.getImage();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 350, 300, this); //감시자
    }
}

public class Graphics02 extends JFrame {
    private MyPanel03 graphicsPanel = new MyPanel03();

    public Graphics02() throws HeadlessException {
        this.setTitle("graphics");
        this.setSize(500, 400);
        this.setContentPane(graphicsPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Graphics02();
    }
}