package practice0a.fruit;

import javax.swing.*;
import java.awt.*;

class ResizeIcon extends ImageIcon {
    private Image image;

    public ResizeIcon(String filename) {
        super(filename);
        image = this.getImage();
    }

    public ImageIcon getResizedImage(int width, int height) {
        Image resizeImage =
                image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizeImage);
    }
}