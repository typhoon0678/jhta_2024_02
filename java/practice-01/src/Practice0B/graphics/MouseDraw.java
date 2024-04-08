package Practice0B.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MouseDraw extends JFrame {

    public MouseDraw() {
        this.setTitle("Mouse Draw");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        MouseDrawPanel mouseDrawPanel = new MouseDrawPanel();
        this.setContentPane(mouseDrawPanel);

        this.setFocusable(true);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MouseDraw();
    }
}


class MouseDrawPanel extends JPanel {

    private final List<Point> startPoints = new ArrayList<>();
    private final List<Point> endPoints = new ArrayList<>();
    private Point oldPoint;

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(new Color(53, 78, 199));

        for (int i = 0; i < startPoints.size(); i++) {
            g.drawLine((int) startPoints.get(i).getX(), (int) startPoints.get(i).getY(),
                    (int) endPoints.get(i).getX(), (int) endPoints.get(i).getY());

            // g.fillOval(x-5, y-5, 10, 10);
        }

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                startPoints.add(oldPoint);
                endPoints.add(e.getPoint());
                oldPoint = e.getPoint();
                repaint();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                repaint();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    startPoints.clear();
                    endPoints.clear();
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (!startPoints.isEmpty()) {
                        startPoints.removeLast();
                        endPoints.removeLast();
                        repaint();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (!startPoints.isEmpty()) {
                        startPoints.removeFirst();
                        endPoints.removeFirst();
                        repaint();
                    }
                }
            }
        });

        this.setFocusable(true);
        this.requestFocus();
    }
}