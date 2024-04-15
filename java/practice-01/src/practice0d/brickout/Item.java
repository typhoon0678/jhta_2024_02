package practice0d.brickout;

import javax.swing.*;
import java.awt.*;

import static practice0d.brickout.Constants.*;

public class Item extends JLabel {

    // Center 좌표
    private int x;
    private int y;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;

        this.setSize(ITEM_SIZE, ITEM_SIZE);
        this.setLocation(x, y);
        ImageIcon lifeImage = new ImageIcon(
                new ImageIcon(IMAGE_PATH).getImage()
                        .getScaledInstance(ITEM_SIZE, ITEM_SIZE, Image.SCALE_SMOOTH));
        this.setIcon(lifeImage);
    }

    @Override
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    static boolean isItemAddable() {
        double percent = 0.5;

        if (Math.random() <= percent) return true;
        else return false;
    }

    public boolean isInItem(int paddleX) {
        return Math.abs(paddleX - this.x) <= (PADDLE_WIDTH + ITEM_SIZE) / 2 &&
                (Math.abs((PANEL_HEIGHT - PADDLE_BOTTOM / 2) - this.y)) <= (PADDLE_HEIGHT + ITEM_SIZE / 2);
    }
}
