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
                                         }
        );

        container.add(label);

        this.setVisible(true);
    }
}

class Practice3 extends JFrame {

    public Practice3() {
        this.setTitle("Left 키로 문자열 교체");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (label.getText().equals("Love Java")) {
                        label.setText("avaJ evoL");
                    } else {
                        label.setText("Love Java");
                    }
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