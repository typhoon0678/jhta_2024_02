package practice0d.picross.play;

import practice0d.picross.record.PixelGrid;
import practice0d.picross.component.PButton;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.Constant.*;

public class PlayPanel extends JPanel implements Runnable {

    private final JPanel infoPanel = new JPanel(new GridLayout(1, 3, GAME_WIDTH / 6, 24));
    private GridPanel gridPanel = null;

    private final JButton backButton = new PButton("이전 화면");
    private final JLabel timerLabel = new JLabel("0초", JLabel.CENTER);
    private final JLabel clearLabel = new JLabel("성공!", JLabel.CENTER);

    private PixelGrid pixelGrid;

    private long startMs;
    private long currentMs;

    private boolean isSelect = false;
    private boolean isClear = false;

    public PlayPanel() {
        this.startMs = System.currentTimeMillis();

        this.setLayout(null);

        clearLabel.setFont(MIDDLE_FONT);
        clearLabel.setForeground(PRIMARY_COLOR);
        backButton.addActionListener(e -> isSelect = true);
        infoPanel.setLocation(0, 0);
        infoPanel.setSize(GAME_WIDTH, INFO_HEIGHT);
        infoPanel.add(backButton);
        infoPanel.add(clearLabel);
        infoPanel.add(timerLabel);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (gridPanel != null && gridPanel.isClear()) {
                this.isClear = true;
                clearLabel.setVisible(true);
            } else {
                currentMs = System.currentTimeMillis();
                timerLabel.setText((currentMs - startMs) / 1000 + "초");
            }
        }
    }

    public void initialSetting(PixelGrid pixelGrid) {
        this.pixelGrid = pixelGrid;
        this.isClear = false;

        this.removeAll();
        this.add(infoPanel);
        clearLabel.setVisible(false);
        gridPanel = new GridPanel(pixelGrid);
        this.add(gridPanel);
        this.add(new CountPanel(pixelGrid, true));
        this.add(new CountPanel(pixelGrid, false));

        this.startMs = System.currentTimeMillis();
        this.timerLabel.setText("0초");
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isClear() {
        return isClear;
    }

    public PixelGrid getPixelGrid() {
        return pixelGrid;
    }

    public long getClearMs() {
        return this.currentMs - this.startMs;
    }
}