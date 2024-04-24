package practice0d.picross.components;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.records.Constant.*;

public class PButton extends JButton {

    public PButton(String title) {
        this.setText(title);
        this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }
}
