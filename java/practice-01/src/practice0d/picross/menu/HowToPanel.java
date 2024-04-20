package practice0d.picross.menu;

import practice0d.picross.component.PButton;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.Constant.*;
import static practice0d.picross.Constant.GAME_HEIGHT;

public class HowToPanel extends JPanel {

    private boolean isMenu = false;

    private final JPanel[] panels = new JPanel[4];

    private final JLabel topHowToLabel = new JLabel();
    private final JLabel middleHowToLabel = new JLabel();
    private final JLabel bottomHowToLabel = new JLabel();

    private final JLabel topHowToText = new JLabel("해당 줄을 숫자가 써있는 만큼 칸을 채웁니다.");
    private final JLabel middleHowToText = new JLabel("왼쪽 클릭 드래그로 색칠합니다.");
    private final JLabel bottomHowToText = new JLabel("오른쪽 클릭 드래그로 X표시를 합니다.");

    private final PButton backToMenuButton = new PButton("돌아가기");

    public HowToPanel() {
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new GridBagLayout());
            this.add(panels[i]);
        }

        topHowToText.setFont(MIDDLE_FONT);
        panels[0].add(topHowToLabel);
        panels[0].add(topHowToText);

        middleHowToText.setFont(MIDDLE_FONT);
        panels[1].add(middleHowToLabel);
        panels[1].add(middleHowToText);

        bottomHowToText.setFont(MIDDLE_FONT);
        panels[2].add(bottomHowToLabel);
        panels[2].add(bottomHowToText);

        panels[3].add(backToMenuButton);

        backToMenuButton.addActionListener(e -> {
            isMenu = true;
        });
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }
}
