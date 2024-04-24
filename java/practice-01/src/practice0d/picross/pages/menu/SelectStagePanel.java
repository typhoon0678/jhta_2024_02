package practice0d.picross.pages.menu;

import practice0d.picross.records.PixelClear;
import practice0d.picross.records.PixelGrid;
import practice0d.picross.components.PButton;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.records.Constant.*;

public class SelectStagePanel extends JPanel {

    private final JPanel[] panels = new JPanel[3];
    private InfoPanel[] infoPanels = new InfoPanel[GRID_DATA.size()];

    private final JButton backButton = new PButton("뒤로");

    private boolean isMenu = false;
    private boolean isPlay = false;

    private PixelGrid pixelGrid;

    public SelectStagePanel() {
        this.setLayout(new BorderLayout());

        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }


        backButton.addActionListener(e -> isMenu = true);
        panels[0].add(backButton);


        panels[1].setLayout(new GridLayout(3, 3, 16, 16));
        for (int i = 0; i < GRID_DATA.size(); i++) {
            infoPanels[i] = new InfoPanel(GRID_DATA.get(i));
            int finalI = i;
            infoPanels[i].button.addActionListener(e -> {
                this.pixelGrid = GRID_DATA.get(finalI);
                isPlay = true;
            });
            panels[1].add(infoPanels[i]);
        }

        panels[2].setLayout(new FlowLayout(FlowLayout.CENTER, 48, 0));

        this.add(panels[0], BorderLayout.NORTH);
        this.add(panels[1], BorderLayout.CENTER);
        this.add(panels[2], BorderLayout.SOUTH);
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    public PixelGrid getPixelGrid() {
        return pixelGrid;
    }

    public void setClearInfo(PixelClear clearData, int index) {
        infoPanels[index].setClearInfo(pixelGrid, clearData);
    }
}