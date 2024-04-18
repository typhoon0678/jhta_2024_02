package practice0d.picross.menu;

import practice0d.picross.component.PButton;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.Constant.*;


public class MenuPanel extends JPanel {

    private boolean isNext = false;


    private final JLabel titleLabel = new JLabel("네모로직 피크로스");
    private final PButton startButton = new PButton("시작");
    private final PButton howToButton = new PButton("설명");

    public MenuPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(BACKGROUND_COLOR);

        titleLabel.setForeground(MAIN_COLOR);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(titleLabel);
        this.add(startButton);
        this.add(howToButton);

        startButton.addActionListener(e -> {
        });

        howToButton.addActionListener(e -> {
            isNext = true;
        });
    }

    public boolean getIsNext() {
        return isNext;
    }

    public void setIsNext(boolean isNext) {
        this.isNext = isNext;
    }
}
