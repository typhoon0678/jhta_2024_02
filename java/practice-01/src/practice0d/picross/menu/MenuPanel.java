package practice0d.picross.menu;

import practice0d.picross.component.PButton;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.Constant.*;


public class MenuPanel extends JPanel {

    private boolean isHowTo = false;
    private boolean isSelect = false;

    private JPanel[] panels = new JPanel[4];

    private final JLabel titleLabel = new JLabel("네모로직 피크로스");
    private final PButton startButton = new PButton("시작");
    private final PButton howToButton = new PButton("설명");

    public MenuPanel() {
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < 4; i++) {
            panels[i] = new JPanel();
            this.add(panels[i]);
        }

        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panels[1].add(titleLabel);

        panels[2].add(startButton);
        panels[3].add(howToButton);

        startButton.addActionListener(e -> {
            isSelect = true;
        });

        howToButton.addActionListener(e -> {
            isHowTo = true;
        });
    }

    public boolean isHowTo() {
        return isHowTo;
    }

    public void setHowTo(boolean howTo) {
        isHowTo = howTo;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
