package practice0d.picross.component;

import practice0d.picross.record.PaintType;

import javax.swing.*;
import java.awt.*;

public class Pixel extends JLabel {

    private PaintType paintType = PaintType.EMPTY;

    public Pixel() {

        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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

    public PaintType getPaintType() {
        return paintType;
    }

    public void setPaintType(PaintType paintType) {
        if (paintType == PaintType.TEMP_EMPTY && this.paintType == PaintType.FILL) {
            this.paintType = PaintType.FILL_TO_EMPTY;
        } else if (paintType == PaintType.TEMP_FILL && this.paintType == PaintType.EMPTY) {
            this.paintType = PaintType.EMPTY_TO_FILL;
        } else if (paintType == PaintType.TEMP_EMPTY && this.paintType == PaintType.BLOCK) {
            this.paintType = PaintType.BLOCK_TO_EMPTY;
        } else if (paintType == PaintType.TEMP_BLOCK && this.paintType == PaintType.EMPTY) {
            this.paintType = PaintType.EMPTY_TO_BLOCK;
        } else {
            this.paintType = paintType;
        }

        setBackground();
    }

    private void setBackground() {
        if (paintType == PaintType.FILL) {
            this.setBackground(Color.black);
        } else if (paintType == PaintType.EMPTY) {
            this.setBackground(Color.white);
        } else if (paintType == PaintType.FILL_TO_EMPTY) {
            this.setBackground(Color.yellow);
        } else if (paintType == PaintType.EMPTY_TO_FILL) {
            this.setBackground(Color.green);
        } else if (paintType == PaintType.BLOCK) {
            this.setBackground(Color.red);
        } else if (paintType == PaintType.EMPTY_TO_BLOCK || paintType == PaintType.BLOCK_TO_EMPTY) {
            this.setBackground(Color.yellow);
        } else {
            this.setBackground(Color.gray);
        }
    }
}