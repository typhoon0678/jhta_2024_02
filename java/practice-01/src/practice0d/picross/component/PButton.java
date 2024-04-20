package practice0d.picross.component;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.Constant.*;

public class PButton extends JButton {

    public PButton(String title) {
        this.setText(title);
        this.setBackground(BUTTON_COLOR);
        this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }
}
