package practice0d.picross.pages.menu;

import practice0d.picross.records.PixelClear;
import practice0d.picross.records.PixelGrid;

import javax.swing.*;
import java.awt.*;

public
class InfoPanel extends JPanel {

    public JButton button = new JButton();
    JLabel infoLabel = new JLabel();

    public InfoPanel(PixelGrid grid) {

        this.setLayout(new BorderLayout());
        button.setText(String.format("문제 크기\n%d X %d", grid.width(), grid.height()));

        this.add(button, BorderLayout.CENTER);
        infoLabel.setText("도전");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(infoLabel, BorderLayout.SOUTH);
    }

    public void setClearInfo(PixelGrid grid, PixelClear clearData) {
        button.removeAll();
        button.setEnabled(false);
        button.setLayout(new GridLayout(grid.width(), grid.height()));

        for (int i = 0; i < grid.width(); i++) {
            for (int j = 0; j < grid.height(); j++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                if (grid.grid()[i][j]) label.setBackground(Color.black);
                else label.setBackground(Color.white);
                button.add(label);
            }
        }

        infoLabel.setText(String.format("성공 : %.1f초", clearData.clearMs() / 1000.0));
    }
}