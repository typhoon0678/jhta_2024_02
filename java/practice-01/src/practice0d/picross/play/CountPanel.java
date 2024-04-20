package practice0d.picross.play;

import practice0d.picross.record.PixelGrid;
import practice0d.picross.component.CountLabel;

import javax.swing.*;

import static practice0d.picross.Constant.*;

public class CountPanel extends JPanel {

    public CountPanel(PixelGrid pixelGrid, boolean isX) {

        this.setLayout(null);

        int sizeX = (int) ((GAME_HEIGHT - INFO_HEIGHT - COUNT_SIZE) / (double) pixelGrid.width());
        int sizeY = (int) ((GAME_HEIGHT - INFO_HEIGHT - COUNT_SIZE) / (double) pixelGrid.height());

        int count = 0;
        int totalCount = 0;

        if (isX) {
            this.setLocation(0, INFO_HEIGHT + COUNT_SIZE);
            this.setSize(GAME_WIDTH - COUNT_SIZE, GAME_HEIGHT - INFO_HEIGHT - COUNT_SIZE);

            int lineX = COUNT_SIZE;
            int lineY = (sizeY - COUNT_TEXT_SIZE) / 2;

            for (int i = 0; i < pixelGrid.width(); i++) {
                for (int j = pixelGrid.height() - 1; j >= 0; j--) {
                    if (pixelGrid.grid()[i][j]) count++;
                    else if (count != 0) {
                        lineX -= COUNT_TEXT_SIZE;
                        this.add(new CountLabel(String.valueOf(count), lineX, lineY));
                        count = 0;
                        totalCount++;
                    }
                }
                if (count != 0) {
                    lineX -= COUNT_TEXT_SIZE;
                    this.add(new CountLabel(String.valueOf(count), lineX, lineY));
                } else if (totalCount == 0) {
                    lineX -= COUNT_TEXT_SIZE;
                    this.add(new CountLabel(String.valueOf(totalCount), lineX, lineY));
                }
                lineX = COUNT_SIZE;
                lineY += sizeY;

                count = 0;
                totalCount = 0;
            }
        } else {
            this.setLocation(COUNT_SIZE, INFO_HEIGHT);
            this.setSize(GAME_WIDTH - COUNT_SIZE, COUNT_SIZE);

            int lineX = (sizeX - COUNT_TEXT_SIZE) / 2;
            int lineY = COUNT_SIZE;

            for (int j = 0; j < pixelGrid.width(); j++) {
                for (int i = pixelGrid.height() - 1; i >= 0; i--) {
                    if (pixelGrid.grid()[i][j]) count++;
                    else if (count != 0) {
                        lineY -= COUNT_TEXT_SIZE;
                        this.add(new CountLabel(String.valueOf(count), lineX, lineY));
                        count = 0;
                        totalCount++;
                    }
                }
                if (count != 0) {
                    lineY -= COUNT_TEXT_SIZE;
                    this.add(new CountLabel(String.valueOf(count), lineX, lineY));
                } else if (totalCount == 0) {
                    lineY -= COUNT_TEXT_SIZE;
                    this.add(new CountLabel(String.valueOf(totalCount), lineX, lineY));
                }
                lineY = COUNT_SIZE;
                lineX += sizeX;

                count = 0;
                totalCount = 0;
            }
        }
    }
}