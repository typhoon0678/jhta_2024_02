package practice0d.brickout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static practice0d.brickout.Constants.*;

class BrickOutPanel extends JPanel implements Runnable {
    private int life = INITIAL_LIFE_COUNT;
    private int remainingBlock = INITIAL_BLOCK_COUNT;

    private final boolean[] isExists = new boolean[INITIAL_BLOCK_COUNT];
    private final Block[] blocks = new Block[INITIAL_BLOCK_COUNT];
    private final Ball ball = new Ball();
    private final Paddle paddle = new Paddle();
    private final Life lifePanel = new Life(life);
    private final GameInfo gameInfoLabel = new GameInfo();

    private boolean isEnd = true;
    private boolean useMouse = true;
    private boolean isPressed = false;
    private boolean isLeft = true;

    public BrickOutPanel() {
        this.setLayout(null);

        for (int i = 0; i < 40; i++) {
            isExists[i] = true;

            blocks[i] = new Block(COLORS[i / 10], i % 10, i / 10);
            this.add(blocks[i]);
        }

        this.setComponentZOrder(lifePanel, 0);
        this.add(lifePanel);
        this.add(gameInfoLabel);

        Thread thread = new Thread(this);
        thread.start();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (life > 0 && isEnd && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    ball.setX(paddle.getX() - BALL_SIZE / 2.0);
                    ball.setY(PANEL_HEIGHT - PADDLE_HEIGHT - PADDLE_BOTTOM - BALL_SIZE / 2.0);
                    ball.setAngle(INITIAL_BALL_ANGLE);
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
                if (useMouse) paddle.setX(e.getX());
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
            g.fillOval(paddle.getX() - BALL_SIZE / 2, PANEL_HEIGHT - PADDLE_HEIGHT - PADDLE_BOTTOM - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE);
        else g.fillOval((int) ball.getX() - BALL_SIZE / 2, (int) ball.getY() - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE);

        g.fillRect(paddle.getX() - PADDLE_WIDTH / 2, PANEL_WIDTH - PADDLE_BOTTOM, PADDLE_WIDTH, PADDLE_HEIGHT);
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

            ball.adjustAngle(paddle.getX(), paddle.isInPaddle(ball.getX(), ball.getY()));

            AdjustGameInfo info = ball.isInBlock(isExists, blocks, remainingBlock);
            if (info.clear()) {
                gameInfoLabel.setText("Clear");
                gameInfoLabel.setVisible(true);
                remainingBlock--;
            } else if (info.blockIndex() != -1) {
                isExists[info.blockIndex()] = false;
                blocks[info.blockIndex()].setVisible(false);
                remainingBlock--;
                speed = ball.adjustSpeed(speed, remainingBlock);
            }

            paddle.adjustPaddleX(speed, useMouse, isPressed, isLeft);

            ball.adjustBallXY(speed);

            checkIsEnd();

            repaint();
        }
    }

    private void checkIsEnd() {
        if (ball.getY() > PANEL_HEIGHT) {
            isEnd = true;
            ball.setY(-1000);
            ball.setAngle(0);
            if (life == 1) gameInfoLabel.setVisible(true);
            lifePanel.setLifeText(--life);
        }
    }
}
