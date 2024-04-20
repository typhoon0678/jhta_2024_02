package practice0d.picross.component;

import javax.swing.*;

import static practice0d.picross.Constant.COUNT_TEXT_SIZE;

public class CountLabel extends JLabel {

    public CountLabel(String text, int x, int y) {
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setText(String.format("%2s", text));
        this.setLocation(x, y);
        this.setSize(COUNT_TEXT_SIZE, COUNT_TEXT_SIZE);
    }
}