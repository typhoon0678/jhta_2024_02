package practice0d.shooting;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    private int x, y, speedX, speedY, width, height;
    private ImageIcon imageIcon;

    public Bullet() {
    }

    public Bullet(ImageIcon imageIcon, int x, int y) {
        this.x = x;
        this.y = y;
        this.imageIcon = imageIcon;
    }

    public Bullet(ImageIcon imageIcon, int x, int y, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.imageIcon = imageIcon;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public int getWidth() {
        return imageIcon.getIconWidth();
    }

    public int getHeight() {
        return imageIcon.getIconHeight();
    }

    public void draw(Graphics g) {
        g.drawImage(imageIcon.getImage(), x, y, null);
    }

    public void moveY(int speedY) {
        y -= speedY;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                ", imageIcon=" + imageIcon +
                '}';
    }
}