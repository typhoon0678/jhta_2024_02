package Practice09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practice9 {

    public static void main(String[] args) {

        new Practice1();
        new Practice2();
        new Practice3();
        new Practice4();
        new Practice5();
        new Practice6();
        new Practice7();
        new Practice8();
    }

}

class Practice1 extends JFrame {

    public Practice1() throws HeadlessException {
        this.setTitle("Training01 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");
        label.setOpaque(true);
        label.setBackground(Color.yellow);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setText("사랑해");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setText("Love Java");
            }
        });

        container.add(label);

        this.setVisible(true);
    }
}

class Practice2 extends JFrame {

    public Practice2() throws HeadlessException {
        this.setTitle("Training02 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Waiting MouseEvent...");
        container.setBackground(Color.green);

        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getY());
                container.setBackground(Color.green);
                label.setText("Waiting MouseEvent...");
            }
        });

        container.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                container.setBackground(Color.yellow);
                label.setText("MouseDragged");
            }
        });

        container.add(label);

        this.setVisible(true);
    }
}

class Practice3 extends JFrame {

    private StringBuffer stringBuffer;

    public Practice3() {
        this.setTitle("Left 키로 문자열 교체");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        stringBuffer = new StringBuffer("Love Java");
        JLabel label = new JLabel(stringBuffer.toString());

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    label.setText(stringBuffer.reverse().toString());
                }
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice4 extends JFrame {

    public Practice4() {
        this.setTitle("Training 04 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    String labelText = label.getText();
                    label.setText(labelText.substring(1) + labelText.charAt(0));
                }
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice5 extends JFrame {

    private int size = 5;

    public Practice5() {
        this.setTitle("Training 05 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");
        label.setOpaque(true);
        label.setBackground(Color.yellow);
        label.setFont(new Font("Arial", Font.PLAIN, size));

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ADD) {
                    size += 5;
                    label.setFont(new Font("Arial", Font.PLAIN, size));
                } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    size = Math.max(size - 5, 5);
                    label.setFont(new Font("Arial", Font.PLAIN, size));
                }
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice6 extends JFrame {

    public Practice6() {
        this.setTitle("Training 06 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(null);

        JLabel label = new JLabel("C");
        label.setSize(10, 10);
        label.setLocation(100, 100);

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    label.setLocation((int) (Math.random() * 280), (int) (Math.random() * 260));
                }
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice7 extends JFrame {

    private int size = 20;

    public Practice7() {
        this.setTitle("Training 07 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");
        label.setOpaque(true);
        label.setBackground(Color.yellow);
        label.setFont(new Font("Arial", Font.PLAIN, size));

        label.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                size = (e.getWheelRotation() < 0) ? size + 1 : size - 1;
                label.setFont(new Font("Arial", Font.PLAIN, size));
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice8 extends JFrame {

    private boolean isPlay = true;
    private int playCount = 0;
    private boolean isCorrect;
    private int value;

    public Practice8() {
        this.setTitle("Open Challenge 10");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        container.setLayout(new GridLayout(2, 1));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));
        bottomPanel.setLayout(new GridBagLayout());

        JLabel[] labels = new JLabel[3];
        JLabel info = new JLabel("시작합니다.");

        for (int i=0; i<3; i++) {
            labels[i] = randomLabel();
            topPanel.add(labels[i]);
        }

        bottomPanel.add(info);

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                isCorrect = true;
                value = -1;

                if (e.getKeyCode() == KeyEvent.VK_ENTER && isPlay) {
                    playCount++;

                    for (JLabel label : labels) {
                        int randomValue = (int) (Math.random() * 5);
                        label.setText(String.valueOf(randomValue));

                        if (value == -1) value = randomValue;
                        else if (value != randomValue) isCorrect = false;
                    }

                    if (isCorrect) {
                        isPlay = false;
                        info.setText("축하합니다." + playCount + "번 만에 맞췄습니다.");
                    }
                    else info.setText("아쉽군요");
                }
            }
        });

        container.add(topPanel);
        container.add(bottomPanel);

        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }

    private JLabel randomLabel() {
        JLabel label = new JLabel(String.valueOf((int) (Math.random() * 5)));
        label.setPreferredSize(new Dimension(50, 50));
        label.setOpaque(true);
        label.setBackground(Color.pink);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    }
}