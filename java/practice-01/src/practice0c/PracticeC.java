package practice0c;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PracticeC {

    public static void main(String[] args) throws FileNotFoundException {

//        new Problem1();
//        new Problem2();
//        new Problem3();
//        new Problem4();
//        new Problem5();
//        new Problem6();
//        new Problem7();
        new Problem8();
    }
}

class Problem1 implements Runnable {

    public Problem1() {
        this.run();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.print("아무 키나 입력 >> ");
        sc.nextLine();

        System.out.println("스레드 실행 시작");
        for (int i = 1; i <= 10; i++) System.out.print(i + " ");
        System.out.println("스레드 종료");

        sc.close();
    }
}

class Problem2 extends JFrame {

    private final boolean isStarted = false;

    public Problem2() {
        this.setTitle("Problem 2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(null);

        Problem2Panel panel = new Problem2Panel();
        Thread thread = new Thread(panel);
        this.setContentPane(panel);


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isStarted) thread.start();
            }
        });

        this.setVisible(true);
    }
}

class Problem2Panel extends JPanel implements Runnable {

    private int x = 100;
    private int y = 100;

    @Override
    public void paintComponent(Graphics g) {
        int size = 50;

        g.setColor(Color.pink);
        g.drawOval(x, y, size, size);
    }

    @Override
    public void run() {
        while (true) {
            x = (int) (Math.random() * 240);
            y = (int) (Math.random() * 240);
            repaint();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Problem3 extends JFrame {

    public Problem3() {
        this.setTitle("Problem 3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);

        this.add(new Problem3Label());

        this.setVisible(true);
    }
}

class Problem3Label extends JLabel implements Runnable {

    public Problem3Label() {
        this.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        this.setText(LocalTime.now().toString());
        this.setHorizontalAlignment(CENTER);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.setText(LocalTime.now().toString().substring(0, 8));
        }
    }
}

class Problem4 extends JFrame implements Runnable {

    private final int x = 600;
    private final int y = 400;
    private final int move = 10;

    public Problem4() {
        this.setTitle("Problem 4");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLocation(x, y);

        Thread thread = new Thread(this);
        thread.start();

        this.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.setLocation(x - move / 2 + (int) (Math.random() * move),
                    y - move / 2 + (int) (Math.random() * move));
        }
    }
}

class Problem5 extends JFrame {

    public Problem5() {
        this.setTitle("Problem 5");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);

        this.add(new Problem5Label());

        this.setVisible(true);
    }
}

class Problem5Label extends JLabel implements Runnable {

    private final int x = 100;
    private final int y = 0;

    public Problem5Label() {
        this.setText("진동 레이블");
        this.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        this.setLocation(x, y);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int move = 10;
            this.setLocation(x - move / 2 + (int) (Math.random() * move),
                    y - move / 2 + (int) (Math.random() * move));
        }
    }
}

class Problem6 extends JFrame {

    public Problem6() {
        this.setTitle("Problem 6");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(null);

        for (int i = 0; i < 6; i++) {
            this.add(new Problem6Label());
        }

        this.setVisible(true);
    }
}

class Problem6Label extends JLabel implements Runnable {

    private int x = (int) (Math.random() * 260);
    private int y = (int) (Math.random() * 50) + 210;
    private final int moveX = 50;
    private int setX = (int) (Math.random() * moveX * 2);

    public Problem6Label() {
        this.setSize(20, 20);
        this.setLocation(x, y);

        this.setIcon(new ImageIcon(
                new ImageIcon("src/practice0b/images/back.jpeg")
                        .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)
        ));

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            setX = ++setX % (moveX * 2);
            x = (setX >= moveX) ? x + 1 : x - 1;
            y -= 1;

            this.setLocation(x, y);

        }
    }
}

class Problem7 extends JFrame {

    public Problem7() {
        this.setTitle("Problem 6");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(null);

        Problem7Panel panel = new Problem7Panel();
        this.setContentPane(panel);

        this.setVisible(true);
    }

}

class Problem7Panel extends JPanel implements Runnable {

    private final ArrayList<Integer> pointX = new ArrayList<>();
    private final ArrayList<Integer> pointY = new ArrayList<>();
    private final ArrayList<Integer> moveX = new ArrayList<>();

    private final int limitX = 20;

    public Problem7Panel() {
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("src/practice0b/images/back.jpeg"));

        this.add(backgroundLabel);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.pink);

        for (int i = 0; i < pointX.size(); i++) {
            g.fillOval(pointX.get(i), pointY.get(i), 10, 10);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pointX.add((int) (Math.random() * 260));
            pointY.add((int) (Math.random() * 20));
            moveX.add((int) (Math.random() * limitX));

            for (int i = 0; i < pointX.size(); i++) {
                int x = (moveX.get(i) < limitX) ? -1 : 1;

                pointX.set(i, pointX.get(i) + x);
                pointY.set(i, pointY.get(i) + 1);
                moveX.set(i, (moveX.get(i) + 1) % (limitX * 2));
            }

            this.repaint();
        }
    }
}

class Problem8 extends JFrame {

    public Problem8() throws FileNotFoundException {
        this.setTitle("떨어지는 단어 맞추기");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 600);

        Problem8Panel wordPanel = new Problem8Panel();

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.gray);
        inputPanel.setLayout(new FlowLayout());
        JTextField textField = new JTextField(15);
        inputPanel.add(textField);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    wordPanel.setAnswer(textField.getText());
                    textField.setText("");
                }
            }
        });

        this.add(inputPanel, BorderLayout.SOUTH);
        this.add(wordPanel);

        this.setVisible(true);

    }
}

class Problem8Panel extends JPanel implements Runnable {

    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<JLabel> labels = new ArrayList<>();
    private String answer = "";

    public Problem8Panel() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/practice0c/words.txt"));

        while (scanner.hasNext()) {
            words.add(scanner.next());
        }

        this.setBackground(Color.lightGray);
        this.setLayout(null);

        Thread thread = new Thread(this);
        thread.start();
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public void run() {
        int limit = 1000;
        int interval = 100;
        int cycle = limit;
        int yMove = 3;

        while (true) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int i = labels.size() - 1; i >= 0 ; i--) {
                if (labels.get(i).getText().equals(answer)) {
                    setAnswer("");
                    this.remove(labels.get(i));
                    labels.remove(i);
                    repaint();
                }
            }

            if (cycle >= limit) {
                cycle = 0;

                JLabel newLabel = new JLabel();
                newLabel.setText(words.get((int) (Math.random() * words.size())));
                newLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
                newLabel.setForeground(Color.magenta);
                newLabel.setSize(100, 20);
                newLabel.setLocation((int) (Math.random() * 200), 0);
                labels.add(newLabel);
                add(newLabel);
            } else {
                cycle += interval;
            }

            for (JLabel label : labels) {
                label.setLocation(label.getX(), label.getY() + yMove);
            }
        }

    }
}