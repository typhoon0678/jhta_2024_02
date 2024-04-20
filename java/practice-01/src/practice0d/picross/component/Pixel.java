package practice0d.picross.component;

import javax.swing.*;
import java.awt.*;

public class Pixel extends JLabel {

    private boolean isFill = false;
    private boolean isNotFill = false;

    public Pixel() {

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setOpaque(true);
        this.setBackground(Color.white);
    }

    public boolean isIn(Point p) {
        return (p.getX() >= this.getX() && p.getX() < this.getX() + this.getWidth() &&
                p.getY() >= this.getY() && p.getY() < this.getY() + this.getHeight());
    }

    public boolean isContain(Point p1, Point p2) {
        double x1 = Math.min(p1.getX(), p2.getX());
        double y1 = Math.min(p1.getY(), p2.getY());
        double x2 = Math.max(p1.getX(), p2.getX());
        double y2 = Math.max(p1.getY(), p2.getY());

        double x3 = this.getX();
        double y3 = this.getY();
        double x4 = this.getX() + this.getWidth();
        double y4 = this.getY() + this.getHeight();

        return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
    }

    public boolean isFill() {
        return isFill;
    }

    public void setFill(boolean fill) {
        isFill = fill;
        this.setBackground((isFill) ? Color.black : Color.white);
    }

    public boolean isNotFill() {
        return isNotFill;
    }

    public void setNotFill(boolean notFill) {
        isNotFill = notFill;
    }
}