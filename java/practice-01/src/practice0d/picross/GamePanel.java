package practice0d.picross;

import practice0d.picross.menu.HowToPanel;
import practice0d.picross.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private final JPanel cards = new JPanel(new CardLayout());
    private final MenuPanel menuCard = new MenuPanel();
    private final HowToPanel howToCard = new HowToPanel();

    CardLayout cardLayout;

    public GamePanel() {
        cards.add(menuCard);
        cards.add(howToCard);
        this.add(cards);

        this.setSize(800, 600);
        this.setVisible(true);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {


        CardLayout cardLayout = (CardLayout) cards.getLayout();

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (menuCard.getIsNext()) {
                cardLayout.next(cards);
                menuCard.setIsNext(false);
            } else if (howToCard.getIsNext()) {
                cardLayout.next(cards);
                howToCard.setIsNext(false);
            }
        }
    }
}
