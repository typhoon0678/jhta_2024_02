package practice0d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class BrickOut extends JFrame {
    public BrickOut() {

        this.setTitle("Brick Out");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 850);
        this.setLocationRelativeTo(null);

        BrickOutPanel panel = new BrickOutPanel();
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new BrickOut();
    }
}

class BrickOutPanel extends JPanel implements Runnable {
    private int life = 3;
    private int remainingBlock = 40;
    private final JLabel lifeLabel = new JLabel(String.valueOf(life));
    private final JLabel gameInfoLabel = new JLabel("Game Over");
    private boolean isEnd = false;

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 800;
    private static final int BLOCK_WIDTH = 80;
    private static final int BLOCK_HEIGHT = 40;
    private static final int PADDLE_BOTTOM = 30;

    private static final Color[] COLORS = {Color.red, Color.yellow, Color.green, Color.blue};
    private static final String IMAGE_PATH = "src/practice0a/fruit/images/apple.png";

    private final boolean[] isExists = new boolean[40];
    private final JLabel[] blocks = new JLabel[40];

    private final int ballSize = 10;
    private double ballX = 400.0;
    private double ballY = 700.0;
    private int angle = 270;

    private final int paddleWidth = 120;
    private static final int PADDLE_HEIGHT = 20;
    private int paddleX = PANEL_WIDTH / 2; // Paddle 중심좌표 x

    private boolean useMouse = true;
    private boolean isPressed = false;
    private boolean isLeft = true;

    public BrickOutPanel() {
        this.setLayout(null);

        for (int i = 0; i < 40; i++) {
            isExists[i] = true;

            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setBackground(COLORS[i / 10]);
            label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            label.setSize(BLOCK_WIDTH, BLOCK_HEIGHT);
            label.setLocation(BLOCK_WIDTH * (i % 10), BLOCK_HEIGHT * (i / 10));

            this.add(label);
            blocks[i] = label;
        }

        JLabel lifeImageLabel = new JLabel();
        lifeImageLabel.setSize(40, 40);
        lifeImageLabel.setLocation(PANEL_WIDTH - 140, PANEL_HEIGHT - PADDLE_BOTTOM - 10);
        ImageIcon lifeImage = new ImageIcon(
                new ImageIcon(IMAGE_PATH).getImage()
                        .getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        lifeImageLabel.setIcon(lifeImage);
        this.add(lifeImageLabel);

        lifeLabel.setSize(80, 20);
        lifeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        lifeLabel.setLocation(PANEL_WIDTH - 80, PANEL_HEIGHT - PADDLE_BOTTOM);
        this.add(lifeLabel);

        gameInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 48));
        gameInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameInfoLabel.setSize(400, 200);
        gameInfoLabel.setLocation((PANEL_WIDTH - 400) / 2, (PANEL_HEIGHT - 200) / 2);
        gameInfoLabel.setVisible(false);
        this.add(gameInfoLabel);

        Thread thread = new Thread(this);
        thread.start();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (life > 0 && isEnd && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    ballX = paddleX - ballSize / 2.0;
                    ballY = PANEL_HEIGHT - PADDLE_HEIGHT - PADDLE_BOTTOM - ballSize / 2.0;
                    angle = 270;
                    isEnd = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    useMouse = !useMouse;
                }

                if (useMouse) return;

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    isPressed = true;
                    isLeft = true;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    isPressed = true;
                    isLeft = false;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                isPressed = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (useMouse) paddleX = e.getX();
            }
        });

        this.setFocusable(true);
        this.requestFocus();
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);

        if (isEnd)
            g.fillRect(paddleX - ballSize / 2, PANEL_HEIGHT - PADDLE_HEIGHT - PADDLE_BOTTOM - ballSize / 2, ballSize, ballSize);
        else g.fillOval((int) ballX - ballSize / 2, (int) ballY - ballSize / 2, ballSize, ballSize);

        g.fillRect(paddleX - paddleWidth / 2, 760, paddleWidth, PADDLE_HEIGHT);
    }

    @Override
    public void run() {
        double speed = 5.0;

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            adjustAngle();

            adjustBallXY(speed);

            checkIsEnd();

            repaint();
        }
    }

    private int setAngleBlockSide(int blockX, int blockY) {
        double left = ballX - blockX;
        double right = blockX + BLOCK_WIDTH - ballX;
        double top = ballY - blockY;
        double bottom = blockY + BLOCK_HEIGHT - ballY;

        if (Math.min(left, right) < Math.min(top, bottom)) return (540 - angle) % 360;
        else return (360 - angle) % 360;

    }

    private void isIn() {

        for (int i = 0; i < 40; i++) {
            if (isExists[i]
                    && ballX >= blocks[i].getX() && ballX <= blocks[i].getX() + BLOCK_WIDTH
                    && ballY >= blocks[i].getY() && ballY <= blocks[i].getY() + BLOCK_HEIGHT) {
                isExists[i] = false;
                if (--remainingBlock == 0) {
                    gameInfoLabel.setText("Clear");
                    gameInfoLabel.setVisible(true);
                    break;
                }
                blocks[i].setVisible(false);
                angle = setAngleBlockSide(blocks[i].getX(), blocks[i].getY());

                break;
            }
        }
    }

    private boolean isInPaddle(double ballX, double ballY) {
        return ballX >= paddleX - paddleWidth / 2.0 && ballX <= paddleX + paddleWidth / 2.0
                && ballY >= PANEL_HEIGHT - (PADDLE_BOTTOM + PADDLE_HEIGHT) && ballY <= PANEL_HEIGHT - PADDLE_BOTTOM;
    }

    private void adjustAngle() {
        if ((ballX <= 0 && (Math.cos(Math.toRadians(angle)) < 0))
                || (ballX >= PANEL_WIDTH && (Math.cos(Math.toRadians(angle)) > 0))) angle = 180 - angle;
        if (ballY <= 0 && (Math.sin(Math.toRadians(angle)) < 0)) angle = -angle;

        isIn();

        if (isInPaddle(ballX, ballY)) {
            angle = (int) (270 + 75 * (ballX - paddleX) * 2 / paddleWidth) % 360;
            System.out.println(angle);
            ballY = PANEL_HEIGHT - PADDLE_BOTTOM - PADDLE_HEIGHT;
        }

        angle = (360 + angle) % 360;
    }

    private void adjustBallXY(double speed) {
        ballX += Math.cos(Math.toRadians(angle)) * speed;
        ballY += Math.sin(Math.toRadians(angle)) * speed;

        if (!useMouse && isPressed && isLeft) paddleX -= (int) speed;
        else if (!useMouse && isPressed) paddleX += (int) speed;
    }

    private void checkIsEnd() {
        if (ballY > PANEL_HEIGHT) {
            isEnd = true;
            ballY = -1000;
            angle = 0;
            if (life == 1) gameInfoLabel.setVisible(true);
            lifeLabel.setText(String.valueOf(--life));
        }
    }
}

