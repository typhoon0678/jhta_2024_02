package practice0d.shooting;

import javax.swing.*;
import java.awt.*;

public class Alien {
    private ImageIcon imageIcon;
    private int x, y, speedX, speedY, width, height;
    private double life;


    public Alien() {
    }

    public Alien(ImageIcon imageIcon, double life, int x, int y) {
        this.imageIcon = imageIcon;
        this.x = x;
        this.y = y;
        this.life = life;
    }

    public Alien(ImageIcon imageIcon, double life, int x, int y, int speedX, int speedY) {
        this.imageIcon = imageIcon;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.life = life;
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
        g.setColor(Color.WHITE);
        //g.drawString(Integer.toString(life),x+10, y-10);
        g.fillRoundRect(x, y - 10, 32, 4, 4, 4); //원래 길이
        g.setColor(Color.RED);
        g.fillRoundRect(x, y - 10, (int) (32 * (life / 3)), 4, 4, 4); //
        g.drawImage(imageIcon.getImage(), x, y, null);
    }

    public void moveX(int speedX) {
        x += speedX;
    }

    public void moveY(int speedY) {
        y += speedY;
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public void reduceLife() {
        if (life > 0) {
            life--;
        }
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