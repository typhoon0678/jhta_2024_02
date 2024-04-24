package practice0d.picross;

import practice0d.picross.pages.menu.HowToPanel;
import practice0d.picross.pages.menu.MenuPanel;
import practice0d.picross.pages.menu.SelectStagePanel;
import practice0d.picross.pages.play.PlayPanel;
import practice0d.picross.records.PixelClear;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.records.Constant.*;

public class GamePanel extends JPanel implements Runnable {

    private final JPanel cards = new JPanel(new CardLayout());
    private final MenuPanel menuCard = new MenuPanel();
    private final HowToPanel howToCard = new HowToPanel();
    private final SelectStagePanel selectCard = new SelectStagePanel();
    private final PlayPanel playPanel = new PlayPanel();

    private PixelClear[] clearData = new PixelClear[GRID_DATA.size()];

    CardLayout cardLayout;

    public GamePanel() {
        cards.add(howToCard, "HowTo");
        cards.add(menuCard, "Menu");
        cards.add(selectCard, "Select");
        cards.add(playPanel, "Play");
        this.add(cards);

        this.setVisible(true);

        for (int i = 0; i < GRID_DATA.size(); i++) {
            clearData[i] = new PixelClear(false, 0);
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, "Menu");

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 페이지
            if (menuCard.isHowTo()) {
                cardLayout.show(cards, "HowTo");
                menuCard.setHowTo(false);
            } else if (menuCard.isSelect()) {
                cardLayout.show(cards, "Select");
                menuCard.setSelect(false);
            } else if (howToCard.isMenu()) {
                cardLayout.show(cards, "Menu");
                howToCard.setMenu(false);
            } else if (selectCard.isMenu()) {
                cardLayout.show(cards, "Menu");
                selectCard.setMenu(false);
            } else if (selectCard.isPlay()) {
                playPanel.initialSetting(selectCard.getPixelGrid());

                cardLayout.show(cards, "Play");
                selectCard.setPlay(false);
            } else if (playPanel.isSelect()) {
                updateClearInfo();

                cardLayout.show(cards, "Select");
                playPanel.setSelect(false);
            }
        }
    }

    private void updateClearInfo() {
        int index = playPanel.getPixelGrid().index();

        clearData[playPanel.getPixelGrid().index()]
                = new PixelClear(playPanel.isClear(), playPanel.getClearMs());

        if (clearData[index].isClear()) {
            selectCard.setClearInfo(clearData[index], index);
        }
    }
}
