package Practice0B.graphics;

import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {


    int posX = 0;
    private Color colors[] = {Color.ORANGE, Color.GREEN, Color.BLUE};

    //JPanel, JButton, JLabel 등등 에 그림을 그릴 수 있다.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                g.setColor(colors[i]); // 테두리 컬러
                g.fillRect(52 * j, 32 * i, 50, 30);
            }
        }
        g.fillRect(100, 250, 50, 50);
        g.setColor(Color.BLACK);
        g.fillOval(200, 200, 20, 20);
        g.drawString("게임오버", 180, 150);
    }


}

public class Paint01 extends JFrame {
    private MyPanel myPanel = new MyPanel(); //컨테이너

    public Paint01() throws HeadlessException {
        this.setTitle("그리기");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(myPanel);
        this.setSize(300, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Paint01();
    }
}