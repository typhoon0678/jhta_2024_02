package practice0d.brickout;

import javax.swing.*;
import java.awt.*;

import static practice0d.brickout.Constants.*;

public class BrickOut extends JFrame {
    public BrickOut() {

        this.setTitle("Brick Out");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT + PADDLE_BOTTOM + PADDLE_HEIGHT);
        this.setLocationRelativeTo(null);

        BrickOutPanel panel = new BrickOutPanel();
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new BrickOut();
    }
}


