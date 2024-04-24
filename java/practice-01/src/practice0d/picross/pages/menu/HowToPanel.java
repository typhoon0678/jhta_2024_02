package practice0d.picross.pages.menu;

import practice0d.picross.components.PButton;
import practice0d.picross.components.Pixel;

import javax.swing.*;
import java.awt.*;

import static practice0d.picross.records.Constant.*;

public class HowToPanel extends JPanel {

    private boolean isMenu = false;

    private final JPanel[] panels = new JPanel[3];

    private final JLabel howToText = new JLabel(
            """
                    <html>
                        각 줄의 칸을 숫자만큼 색칠합니다.
                        <br><br>왼쪽 클릭 후 드래그하여 칸을 색칠합니다.
                        <br><br>색칠된 칸을 드래그하여 칸을 지웁니다.
                        <br><br>
                        <br><br>오른쪽 클릭 후 드래그로 칠하지 않을 칸을 지정할 수 있습니다.
                    </html>""");

    private final PButton backToMenuButton = new PButton("돌아가기");

    public HowToPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new GridBagLayout());
            this.add(panels[i]);
        }

        JPanel examplePanel = new JPanel();
        examplePanel.setLayout(new GridLayout(1, 11, 4, 0));
        examplePanel.setPreferredSize(new Dimension(GAME_WIDTH, GAME_WIDTH / 11));
        examplePanel.add(new JLabel("3  3  2"));
        for (int i = 0; i < 10; i++) {
            Pixel pixel = new Pixel();
            if (i == 3 || i == 7) pixel.setBackground(Color.white);
            else pixel.setBackground(Color.black);
            examplePanel.add(pixel);
        }
        panels[0].add(examplePanel);

        howToText.setFont(MIDDLE_FONT);
        panels[1].add(howToText);

        panels[2].add(backToMenuButton);

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
