package practice0d.picross;

import javax.swing.*;

import static practice0d.picross.Constant.*;

public class Game extends JFrame {

    public Game() {
        this.setTitle("Picross");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setContentPane(new GamePanel());

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Game();
    }
}
