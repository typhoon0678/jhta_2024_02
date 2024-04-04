package Practice0A.fruit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Fruit extends JFrame {
    private final String[] fruits;
    private int count = 1;

    public Fruit() throws HeadlessException {
        fruits = new String[]{"apple", "avocado", "banana", "cherries", "dragon-fruit", "grape", "lemon", "mango", "orange", "watermelon"};
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel mainPanel = new JPanel();

        JLabel title = new JLabel("당신이 제일 좋아하는 과일은?");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        container.add(title, BorderLayout.NORTH);

        JLabel leftImageLabel = new JLabel();
        ResizeIcon leftResizeIcon = new ResizeIcon("src/Practice0A/fruit/images/" + fruits[0] + ".png");
        leftImageLabel.setIcon(leftResizeIcon.getResizedImage(200, 200));
        leftPanel.add(leftImageLabel);

        JLabel rightImageLabel = new JLabel();
        ResizeIcon rightResizeIcon = new ResizeIcon("src/Practice0A/fruit/images/" + fruits[1] + ".png");
        rightImageLabel.setIcon(rightResizeIcon.getResizedImage(200, 200));
        rightPanel.add(rightImageLabel);

        leftPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (++count >= fruits.length) {
                    rightPanel.setVisible(false);
                    leftPanel.removeMouseListener(this);
                } else {
                    ResizeIcon rightImageIcon = new ResizeIcon("src/Practice0A/fruit/images/" + fruits[count] + ".png");
                    rightImageLabel.setIcon(rightImageIcon.getResizedImage(200, 200));
                }
            }
        });

        rightPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (++count >= fruits.length) {
                    leftPanel.setVisible(false);
                    rightPanel.removeMouseListener(this);
                } else {
                    ResizeIcon leftImageIcon = new ResizeIcon("src/Practice0A/fruit/images/" + fruits[count] + ".png");
                    leftImageLabel.setIcon(leftImageIcon.getResizedImage(200, 200));
                }
            }
        });

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        container.add(mainPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}