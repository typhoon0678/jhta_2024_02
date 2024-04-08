package Practice0B;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PracticeB {
    public static void main(String[] args) {
//        new Problem1();
//        new Problem2();
//        new Problem3();
//        new Problem4();
//        new Problem5();
//        new Problem6();
//        new Problem7();
//        new Problem8();
        new Problem9();
    }
}

class Problem1 extends JFrame {

    private Problem1Panel imagePanel = new Problem1Panel();
    private boolean isVisible = true;

    public Problem1() {
        this.setTitle("12장 Open Challenge");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setContentPane(imagePanel);


        Container container = new Container();
        container.setLayout(new FlowLayout());

        JButton imageButton = new JButton("Hide / Show");
        imageButton.addActionListener(e -> {
            isVisible = !isVisible;
            imagePanel.changeIsVisible(isVisible);
        });

        this.add(container);
        container.add(imageButton);
        this.setVisible(true);
    }
}

class Problem1Panel extends JPanel {
    private final ImageIcon imageIcon = new ImageIcon("src/Practice0B/images/back.jpeg");
    private Image image = imageIcon.getImage();

    public Problem1Panel() {
        this.setLayout(new FlowLayout());
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, 300, 300, Color.gray, this);
    }

    public void changeIsVisible(boolean isVisible) {
        image = isVisible ? imageIcon.getImage() : null;
        this.repaint();
    }
}

class Problem2 extends JFrame {

    private Problem2Panel imagePanel = new Problem2Panel();

    public Problem2() {
        this.setTitle("실습예제 2번");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        this.setContentPane(imagePanel);

        this.setVisible(true);
    }
}

class Problem2Panel extends JPanel {
    private Image image = new ImageIcon("src/Practice0B/images/back.jpeg").getImage();
    private int x = 150;
    private int y = 150;

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, 300, 300, Color.gray, this);

        g.setColor(Color.green);
        g.fillOval(x, y, 10, 10);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                repaint();
            }
        });

    }
}

class Problem3 extends JFrame {

    private int x = 100;
    private int y = 100;
    private final int size = 50;
    private final int yDummy = 30;

    public Problem3() {
        this.setTitle("실습예제 3번");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JLabel imageLabel = new JLabel();
        imageLabel.setLocation(x, y);
        imageLabel.setSize(size, size);
        Image scaledImage = new ImageIcon("src/Practice0B/images/back.jpeg")
                .getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getX() >= x && e.getX() <= x + size
                        && e.getY() - yDummy >= y && e.getY() - yDummy <= y + size) {
                    x = e.getX() - size / 2;
                    y = e.getY() - yDummy - size / 2;
                    imageLabel.setLocation(x, y);
                }
            }
        });

        this.add(imageLabel);
        this.setVisible(true);
    }
}

class Problem4 extends JFrame {

    public Problem4() {
        this.setTitle("실습예제 5번");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        Problem4Panel imagePanel = new Problem4Panel();
        this.setContentPane(imagePanel);

        this.setVisible(true);
    }
}

class Problem4Panel extends JPanel {

    private final ImageIcon imageIcon = new ImageIcon("src/Practice0B/images/back.jpeg");
    private Image image = imageIcon.getImage();
    private double width = imageIcon.getIconWidth();
    private double height = imageIcon.getIconHeight();

    public void paintComponent(Graphics g) {
        g.drawImage(image, 10, 10, (int) width, (int) height, Color.gray, this);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ADD) {
                    width *= 1.1;
                    height *= 1.1;
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    width /= 1.1;
                    height /= 1.1;
                    repaint();
                }
            }
        });

        this.setFocusable(true);
        this.requestFocus();
    }

}

class Problem5 extends JFrame {

    public Problem5() {
        this.setTitle("12장 연습하기");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        Problem5Panel linePanel = new Problem5Panel();
        this.setContentPane(linePanel);

        this.setVisible(true);

    }
}

class Problem5Panel extends JPanel {

    public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0));

        int interval = 30;
        int size = 300;

        for (int i = interval; i < size; i += interval) {
            g.drawLine(i, 0, i, 300);
            g.drawLine(0, i, 300, i);
        }
    }

}

class Problem6 extends JFrame {

    public Problem6() {
        this.setTitle("12장 연습하기");
        this.setSize(300, 300 + 30);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        Problem6Panel linePanel = new Problem6Panel();
        this.setContentPane(linePanel);

        this.setVisible(true);

    }
}

class Problem6Panel extends JPanel {

    public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0));

        int interval = 10;
        int middle = 150;

        for (int i = 0; i < 10 * interval; i += interval) {
            int[] x = {i, middle, 2 * middle - i, middle};
            int[] y = {middle, i, middle, 2 * middle - i};

            g.drawPolygon(x, y, 4);
        }
    }

}

class Problem7 extends JFrame {

    public Problem7() {
        this.setTitle("12장 연습하기");
        this.setSize(300, 300 + 30);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        Problem7Panel linePanel = new Problem7Panel();
        this.setContentPane(linePanel);

        this.setVisible(true);

    }
}

class Problem7Panel extends JPanel {

    private List<Point> points = new ArrayList<>();

    public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0));

        if (points.size() >= 2) {
            for (int i = 0; i < points.size() - 2; i++) {
                g.drawLine((int) points.get(i).getX(), (int) points.get(i).getY(),
                        (int) points.get(i + 1).getX(), (int) points.get(i + 1).getY());
            }
            g.drawLine((int) points.get(points.size() - 1).getX(), (int) points.get(points.size() - 1).getY(),
                    (int) points.get(0).getX(), (int) points.get(0).getY());
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });
    }

}

class Problem8 extends JFrame {

    public Problem8() {
        this.setTitle("12장 연습하기");
        this.setSize(300, 300 + 30);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        Problem8Panel linePanel = new Problem8Panel();
        this.setContentPane(linePanel);

        this.setVisible(true);

    }
}

class Problem8Panel extends JPanel {

    private List<Point> startPoints = new ArrayList<>();
    private List<Point> endPoints = new ArrayList<>();

    public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0));

        int width, height, startX, startY;

        for (int i = 0; i < startPoints.size(); i++) {
            width = (int) (Math.abs(startPoints.get(i).getX() - endPoints.get(i).getX()));
            height = (int) (Math.abs(startPoints.get(i).getY() - endPoints.get(i).getY()));
            startX = (int) Math.min(startPoints.get(i).getX(), endPoints.get(i).getX());
            startY = (int) Math.min(startPoints.get(i).getY(), endPoints.get(i).getY());

            g.drawOval(startX, startY, width, height);
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (startPoints.size() <= endPoints.size()) {
                    startPoints.add(new Point(e.getX(), e.getY()));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (startPoints.size() > endPoints.size()) {
                    endPoints.add(new Point(e.getX(), e.getY()));
                }
                repaint();
            }
        });
    }
}

class Problem9 extends JFrame {

    private final float[] percents = new float[]{0, 0, 0, 100};
    private Color[] colors = new Color[]{Color.black, Color.black, Color.black, Color.black};

    private final String[] fruits = new String[]{"apple", "cherry", "strawberry", "prune"};
    private final JTextField[] fields = new JTextField[4];
    private final JLabel[] percentLabels = new JLabel[4];

    public Problem9() {
        this.setTitle("12장 연습하기");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 16, 0));
        inputPanel.setBackground(Color.lightGray);

        JPanel graphPanel = new JPanel();
        graphPanel.setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 16, 0));

        graphPanel.add(labelPanel, BorderLayout.NORTH);

        Problem9Panel piePanel = new Problem9Panel();
        this.setContentPane(piePanel);

        JLabel[] labels = new JLabel[4];
        for (int i = 0; i < fields.length; i++) {
            labels[i] = new JLabel(fruits[i], JLabel.CENTER);
            fields[i] = new JTextField("0", 5);
            inputPanel.add(labels[i]);
            inputPanel.add(fields[i]);
        }

        for (int i = 0; i < labels.length; i++) {
            percentLabels[i] = new JLabel(fruits[i], JLabel.CENTER);
            labelPanel.add(percentLabels[i]);
        }

        fields[3].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    float sum = 0;
                    for (JTextField field : fields) {
                        sum += Float.parseFloat(field.getText());
                    }

                    colors = new Color[]{Color.red, Color.blue, Color.pink, Color.yellow};

                    for (int i = 0; i < 4; i++) {
                        percentLabels[i].setForeground(colors[i]);
                        percents[i] = Float.parseFloat(fields[i].getText()) / sum * 100;
                        percentLabels[i].setText(String.format("%s %.1f%%", fruits[i], percents[i]));
                    }

                    piePanel.setPercents(percents);
                    piePanel.setColors(colors);
                    piePanel.repaint();
                }
            }
        });

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(graphPanel, BorderLayout.CENTER);

        this.setVisible(true);

    }

}

class Problem9Panel extends JPanel {

    private float[] percents = new float[]{0, 0, 0, 100};
    private Color[] colors = new Color[]{Color.black, Color.black, Color.black, Color.black};

    public void paintComponent(Graphics g) {
        int angle = 0;

        for (int i = 0; i < 4; i++) {
            int addAngle = Math.round(percents[i] * 360 / 100);

            g.setColor(colors[i]);
            g.fillArc(260, 200, 300, 300, angle, addAngle);
            angle += addAngle;
        }
    }

    public void setPercents(float[] percents) {
        this.percents = percents;
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }

}