package practice0d.brickout;

import javax.swing.*;
import java.awt.*;

import static practice0d.brickout.Constants.PANEL_HEIGHT;
import static practice0d.brickout.Constants.PANEL_WIDTH;

class GameInfo extends JLabel {

    public GameInfo() {
        this.setText("Game Over");
        this.setFont(new Font("맑은 고딕", Font.BOLD, 48));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setSize(400, 200);
        this.setLocation((PANEL_WIDTH - 400) / 2, (PANEL_HEIGHT - 200) / 2);
        this.setVisible(false);
    }
}
