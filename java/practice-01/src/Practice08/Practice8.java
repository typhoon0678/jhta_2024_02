package Practice08;
import javax.swing.*;
import java.awt.*;

public class Practice8 {

    public static void main(String[] args) {

        new MyFrame1();
        new MyFrame2();
        new MyFrame3();
        new MyFrame4(new Color[] {Color.red, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue, Color.magenta, Color.darkGray, Color.pink, Color.gray});
        new MyFrame5(new Color[] {Color.red, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue, Color.magenta, Color.darkGray, Color.pink, Color.gray});
        new MyFrame6();
        new MyFrame7();
        new MyFrame8();

    }

}

class MyFrame1 extends JFrame {
    public MyFrame1() {
        this.setSize(400, 200);
        this.setTitle("Let's study Java");

        setVisible(true);
    }
}

class MyFrame2 extends JFrame {
    public MyFrame2() {
        this.setSize(400, 200);
        this.setTitle("BorderLayout Practice");

        setLayout(new BorderLayout(5, 7));
        add(new Button("North"), BorderLayout.NORTH);
        add(new Button("West"), BorderLayout.WEST);
        add(new Button("Center"), BorderLayout.CENTER);
        add(new Button("East"), BorderLayout.EAST);
        add(new Button("South"), BorderLayout.SOUTH);

        setVisible(true);
    }
}

class MyFrame3 extends JFrame {
    public MyFrame3() {
        this.setSize(600, 480);
        this.setTitle("BorderLayout Practice");

        setLayout(new GridLayout(1, 10));
        for (int i = 1; i <= 10; i++) {
            add(new Button(String.valueOf(i)));
        }

        setVisible(true);
    }
}

class MyFrame4 extends JFrame {

    public MyFrame4(Color[] colorList) {

        this.setSize(600, 480);
        this.setTitle("Ten Color Buttons Frame");

        setLayout(new GridLayout(1, 10));
        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBackground(colorList[i % colorList.length]);
            add(btn);
        }

        setVisible(true);
    }
}

class MyFrame5 extends JFrame {

    public MyFrame5(Color[] colorList) {

        this.setSize(800, 600);
        this.setTitle("Ten Color Buttons Frame");

        setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBackground(colorList[i % colorList.length]);
            add(btn);
        }

        setVisible(true);
    }
}

class MyFrame6 extends JFrame {

    public MyFrame6() {

        this.setSize(300, 300);
        this.setTitle("Let's study Java");
        this.setLayout(null);

        for (int i = 0; i < 20; i++) {
            JLabel label = new JLabel(String.valueOf(i));

            label.setSize(10, 10);
            label.setOpaque(true);
            label.setBackground(Color.red);
            label.setLocation(40 + (int) (Math.random() * 200), 40 + (int) (Math.random() * 200));

            add(label);
        }

        setVisible(true);
    }
}

class MyFrame7 extends JFrame {

    public MyFrame7() {
        this.setSize(300, 300);
        this.setTitle("계산기프레임");
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JButton btn;
        JLabel label;
        String[] btnNameList = {"CE", "계산", "+", "-", "x", "%"};

        topPanel.setBackground(Color.lightGray);
        label = new JLabel("수식입력");
        topPanel.add(label);
        topPanel.add(new TextField(12));

        centerPanel.setLayout(new GridLayout(4, 4, 4, 4));
        for (int i=0; i<=9; i++) {
            btn = new JButton(String.valueOf(i));
            btn.setBackground(Color.white);
            centerPanel.add(btn);
        }
        for (String name : btnNameList) {
            btn = new JButton(name);
            btn.setBackground(Color.white);
            centerPanel.add(btn);
        }

        bottomPanel.setBackground(Color.lightGray);
        bottomPanel.add(new Label("계산결과"));
        bottomPanel.add(new TextField(12));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

class MyFrame8 extends JFrame {

    public MyFrame8() {
        this.setSize(300, 300);
        this.setTitle("여러 개의 패널을 가진 프레임");
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JButton btn;
        JLabel label;
        String[] btnNameList = {"Open", "Close", "Exit"};

        topPanel.setBackground(Color.lightGray);
        for (String name : btnNameList) {
            btn = new JButton(name);
            btn.setBackground(Color.white);
            topPanel.add(btn);
        }

        centerPanel.setLayout(null);
        for (int i=0; i<10; i++) {
            label = new JLabel("*");
            label.setSize(8, 8);
            label.setForeground(Color.green);
            label.setLocation((int) (Math.random() * 290), (int) (Math.random() * 160));
            centerPanel.add(label);
        }

        bottomPanel.setBackground(Color.lightGray);
        btn = new JButton("Integer Input");
        btn.setBackground(Color.white);
        bottomPanel.add(btn);
        bottomPanel.add(new TextField(16));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}