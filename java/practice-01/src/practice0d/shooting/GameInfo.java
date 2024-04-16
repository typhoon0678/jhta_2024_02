package practice0d.shooting;

import javax.swing.*;
import java.awt.*;

import static practice0d.shooting.GamePanel.GAME_WIDTH;
import static practice0d.shooting.GamePanel.GAME_HEIGHT;

public class GameInfo extends JLabel {

    public GameInfo(int life, int score) {
        this.setText(String.format("Life : %d    Score : %d", life, score));
        this.setFont(new Font("맑은 고딕",Font.BOLD,24));
        this.setForeground(Color.white);
        this.setSize(300, 30);
        this.setLocation(GAME_WIDTH - 300, GAME_HEIGHT - 30);
    }

    public void setText(int life, int score) {
        this.setText(String.format("Life : %d    Score : %d", life, score));
    }
}
