package Practice0B.graphics;

import javax.swing.*;
import java.awt.*;

class MyPanel02 extends JPanel {
    public void paintComponent(Graphics g) {
        //여기다가 그림그리기....
        //1. 컬러정하기
        //2. drawRect, fillRect
        g.setColor(new Color(53, 78, 199));
        g.fillRect(100,200,20,200);

        g.setColor(new Color(213, 23, 210));
        g.fillRect(130,200,20,200);

        //글자 그리기. 1. 컬러정하기, 2. 폰트 정하기, 3. g.drawString
        g.setColor(new Color(199, 82, 34));
        g.setFont(new Font("맑은 고딕",Font.BOLD,30));
        g.drawString("JAVA",200,100);

        g.drawLine(150,150,300,300);
        g.fillOval(300,300,100,100);
        g.drawRoundRect(300,20,100,100,30,30);
        g.fillArc(300,200,100,100,30,300);
        int x [] =  {100,50,100,150};
        int y [] =  {100,150,200,150};

        g.drawPolygon(x,y,3); // 폐곡선


    }
}
public class Graphics01 extends JFrame {
    public Graphics01() throws HeadlessException {
        this.setTitle("graphics");
        this.setSize(500,400);
        //Container container = this.getContentPane();

        MyPanel02 graphicsPanel = new MyPanel02();
        this.setContentPane(graphicsPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Graphics01();
    }
}