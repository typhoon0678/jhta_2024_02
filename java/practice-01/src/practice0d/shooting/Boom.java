package practice0d.shooting;

import javax.swing.*;
import java.awt.*;

public class Boom {
    private ImageIcon imageIcon;
    private int x, y, speedX, speedY, width, height;


    public Boom() {
    }

    public Boom(ImageIcon imageIcon, int x, int y) {
        this.imageIcon = imageIcon;
        this.x = x;
        this.y = y;
    }

    public Boom(ImageIcon imageIcon, int x, int y, int speedX, int speedY) {
        this.imageIcon = imageIcon;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
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

    public int getWidth() {
        return imageIcon.getIconWidth();
    }

    public int getHeight() {
        return imageIcon.getIconHeight();
    }

    public void draw(Graphics g) {
        g.drawImage(imageIcon.getImage(), x, y, null);
    }

    public void moveX(int speedX) {
        x += speedX;
    }

    public void moveY(int speedY) {
        y += speedY;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "imageIcon=" + imageIcon +
                ", x=" + x +
                ", y=" + y +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                '}';
    }
}