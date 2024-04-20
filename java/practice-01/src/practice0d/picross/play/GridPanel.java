package practice0d.picross.play;

import practice0d.picross.record.PixelGrid;
import practice0d.picross.component.Pixel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static practice0d.picross.Constant.*;

public class GridPanel extends JPanel {

    private final Pixel[][] pixels;
    private Point pressPoint = new Point();

    private boolean fill = true;
    private boolean isPressed = false;

    private boolean isClear = false;

    public GridPanel(PixelGrid pixelGrid) {

        this.setSize(GAME_WIDTH - COUNT_SIZE, GAME_HEIGHT - INFO_HEIGHT - COUNT_SIZE);
        this.setLocation(COUNT_SIZE, COUNT_SIZE + INFO_HEIGHT);
        this.setBackground(Color.yellow);

        this.setLayout(new GridLayout(pixelGrid.width(), pixelGrid.height()));

        pixels = new Pixel[pixelGrid.width()][pixelGrid.height()];
        for (int i = 0; i < pixelGrid.width(); i++) {
            for (int j = 0; j < pixelGrid.height(); j++) {
                pixels[i][j] = new Pixel();
                this.add(pixels[i][j]);
            }
        }


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isClear) return;

                pressPoint = e.getPoint();
                isPressed = true;

                for (Pixel[] pixelList : pixels) {
                    for (Pixel pixel : pixelList) {
                        if (pixel.isIn(pressPoint)) {
                            fill = !pixel.isFill();
                            return;
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isClear) return;
                isPressed = false;

                for (int i = 0; i < pixelGrid.width(); i++) {
                    for (int j = 0; j < pixelGrid.height(); j++) {
                        if (pixels[i][j].isFill() != pixelGrid.grid()[i][j]) return;
                    }
                }
                isClear = true;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isClear || !isPressed) return;

                for (Pixel[] pixelList : pixels) {
                    for (Pixel pixel : pixelList) {
                        if (pixel.isContain(pressPoint, e.getPoint())) {
                            pixel.setFill(fill);
                        }
                    }
                }
            }
        });
    }

    public boolean isClear() {
        return isClear;
    }
}