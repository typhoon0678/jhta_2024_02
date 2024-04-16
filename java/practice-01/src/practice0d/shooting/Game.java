package practice0d.shooting;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    GamePanel gamePanel = new GamePanel();

    public Game() throws HeadlessException {
        this.setTitle("SHOOTING-GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gamePanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/practice0d/images/alien.png");
        this.setIconImage(icon.getImage());
    }

    public static void main(String[] args) {
        new Game();
    }
}