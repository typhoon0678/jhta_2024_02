package practice0d.brickout;

import javax.swing.*;
import java.awt.*;

import static practice0d.brickout.Constants.*;

public class KeyInfo extends JLabel {

    public KeyInfo() {
        int width = 560;
        int height = PADDLE_BOTTOM;

        this.setSize(width, height + 10);
        this.setLocation(32, PANEL_HEIGHT);


        JLabel keyLabel = new JLabel("ENTER : 마우스 / 키보드 교체, SPACE : 시작, ESC : 일시정지");

        keyLabel.setSize(width, height);
        keyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        this.add(keyLabel);
    }
}
