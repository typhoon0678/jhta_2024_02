package practice0d.shooting;

import javax.swing.*;
import java.awt.*;

public class Player {
    //ImageIcon, x,y,speedX, speedY
    private ImageIcon imageIcon;
    private int x,y,speedX,speedY, life, width,height;

    private ImageIcon heart = new ImageIcon("images/heart.png");
    // new 할때 실행되는 함수
    public Player() {

    }

    public Player(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public Player(ImageIcon imageIcon, int x, int y) {
        this.imageIcon = imageIcon;
        this.x = x;
        this.y = y;
        life = 3;
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

    public void draw(Graphics g) {
        for(int i=0;i<life;i++) {
            g.drawImage(heart.getImage(),35*i+50,30,null);
        }
        g.drawImage(imageIcon.getImage(), x,y,null);
    }

    public void moveX(int speedX) {
        int rightEnd = GamePanel.GAME_WIDTH - imageIcon.getIconWidth();
        x+=speedX;
        if(x>=rightEnd) {
            x=rightEnd;
        }
        if(x<=0) {
            x=0;
        }
    }
    public void moveY(int speedY) {
        int bottomEnd = GamePanel.GAME_HEIGHT - imageIcon.getIconHeight();
        y+=speedY;
        if(y>=bottomEnd) {
            y= bottomEnd;
        }
        if(y<=0) {
            y=0;
        }
    }


    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getWidth() {
        return imageIcon.getIconWidth();
    }

    public int getHeight() {
        return imageIcon.getIconHeight();
    }
    public void reduceLife() {
        if(life>0) {
            life--;
        }
    }
    public void addLife() {
        if(life < 5) {
            life++;
        }
    }


    @Override
    public String toString() {
        return "Player{" +
                "imageIcon=" + imageIcon +
                ", x=" + x +
                ", y=" + y +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                '}';
    }
}