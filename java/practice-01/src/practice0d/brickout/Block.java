package practice0d.brickout;

import javax.swing.*;
import java.awt.*;

import static practice0d.brickout.Constants.*;

class Block extends JLabel {

    public Block(Color color, int x, int y) {
        this.setOpaque(true);
        this.setBackground(color);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.setSize(BLOCK_WIDTH, BLOCK_HEIGHT);
        this.setLocation(BLOCK_WIDTH * x, BLOCK_HEIGHT * y);
    }
}
