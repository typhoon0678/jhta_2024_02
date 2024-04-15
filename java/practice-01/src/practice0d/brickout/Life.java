package practice0d.brickout;

import javax.swing.*;
import java.awt.*;

import static practice0d.brickout.Constants.*;

class Life extends JPanel {

    private final JLabel lifeLabel = new JLabel();

    public Life(int life) {
        int width = 200;
        int height = PADDLE_BOTTOM - 10;

        this.setSize(width, height);
        this.setLocation(PANEL_WIDTH - width, PANEL_HEIGHT);

        JLabel lifeImageLabel = new JLabel();
        lifeImageLabel.setSize(40, height);
        ImageIcon lifeImage = new ImageIcon(
                new ImageIcon(IMAGE_PATH).getImage()
                        .getScaledInstance(height, height, Image.SCALE_SMOOTH));
        lifeImageLabel.setIcon(lifeImage);
        this.add(lifeImageLabel);

        lifeLabel.setText(String.valueOf(life));
        lifeLabel.setSize(80, height);
        lifeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        this.add(lifeLabel);
    }

    public void setLifeText(int life) {
        lifeLabel.setText(String.valueOf(life));
    }
}
