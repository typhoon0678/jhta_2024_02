package practice0d.brickout;

import javax.swing.*;
import java.awt.*;

import static practice0d.brickout.Constants.*;

public class StartMenu extends JPanel {

    public StartMenu() {
        this.setLayout(new GridLayout(5, 1));
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT + PADDLE_BOTTOM + PADDLE_HEIGHT);
        this.setBackground(Color.black);
        this.setLocation(0, 0);

        JLabel titleLabel = new JLabel("Brick Out");
        titleLabel.setFont(new Font("맑은 고딕",Font.BOLD,30));
        titleLabel.setForeground(Color.white);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel infoLabel = new JLabel("Press Enter to Start");
        infoLabel.setFont(new Font("맑은 고딕",Font.BOLD,24));
        infoLabel.setForeground(Color.white);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(new JLabel());
        this.add(titleLabel);
        this.add(new JLabel());
        this.add(infoLabel);
        this.add(new JLabel());
    }
}
