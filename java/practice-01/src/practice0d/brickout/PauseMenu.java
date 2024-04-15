package practice0d.brickout;

import javax.swing.*;
import java.awt.*;

import static practice0d.brickout.Constants.PADDLE_HEIGHT;
import static practice0d.brickout.Constants.*;

public class PauseMenu extends JPanel {

    public PauseMenu() {
        this.setLayout(new GridLayout(3, 1));
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT + PADDLE_BOTTOM + PADDLE_HEIGHT);
        this.setBackground(new Color(0, 0, 0, 30));
        this.setLocation(0, 0);

        JLabel infoLabel = new JLabel("Resume : Press ESC");
        infoLabel.setFont(new Font("맑은 고딕",Font.BOLD,24));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(new JLabel());
        this.add(infoLabel);
        this.add(new JLabel());

    }
}
