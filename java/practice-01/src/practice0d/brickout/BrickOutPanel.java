package practice0d.brickout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import static practice0d.brickout.Constants.*;

class BrickOutPanel extends JPanel implements Runnable {
    private int life = INITIAL_LIFE_COUNT;
    private int remainingBlock = INITIAL_BLOCK_COUNT;

    private final boolean[] isExists = new boolean[INITIAL_BLOCK_COUNT];
    private final Block[] blocks = new Block[INITIAL_BLOCK_COUNT];
    private final ArrayList<Item> items = new ArrayList<>();
    private final Ball ball = new Ball();
    private final Paddle paddle = new Paddle();
    private final Life lifePanel = new Life(life);
    private final GameInfo gameInfoLabel = new GameInfo();

    private final StartMenu startMenuPanel = new StartMenu();
    private final PauseMenu pauseMenuPanel = new PauseMenu();

    private boolean isEnd = true;
    private boolean useMouse = true;
    private boolean isPressed = false;
    private boolean isLeft = true;

    private boolean isMenu = true;
    private boolean isPause = false;

    public BrickOutPanel() {
        this.setLayout(null);

        pauseMenuPanel.setVisible(false);

        for (int i = 0; i < 40; i++) {
            isExists[i] = true;

            blocks[i] = new Block(COLORS[i / 10], i % 10, i / 10);
            this.add(blocks[i]);
        }

        this.add(lifePanel);
        this.add(gameInfoLabel);
        this.add(new KeyInfo());
        this.add(startMenuPanel);
        this.add(pauseMenuPanel);

        this.setComponentZOrder(startMenuPanel, 0);
        this.setComponentZOrder(pauseMenuPanel, 1);

        Thread thread = new Thread(this);
        thread.start();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (life > 0 && isEnd && !isMenu && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    ball.setX(paddle.getX());
                    ball.setY(PANEL_HEIGHT - PADDLE_HEIGHT - PADDLE_BOTTOM - BALL_SIZE / 2.0);
                    ball.setAngle(INITIAL_BALL_ANGLE);
                    isEnd = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (isMenu) {
                        startMenuPanel.setVisible(false);
                        isMenu = false;
                    } else useMouse = !useMouse;
                }

                // 일시정지
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    isPause = !isPause;
                    pauseMenuPanel.setVisible(isPause);
                }

                if (isPause && e.getKeyCode() == KeyEvent.VK_M) {
                    startMenuPanel.setVisible(true);
                    pauseMenuPanel.setVisible(false);
                    isMenu = true;
                    isPause = false;
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

        if (isEnd) {
            int x = paddle.getX() - BALL_SIZE / 2;
            int y = PANEL_HEIGHT - PADDLE_HEIGHT - BALL_SIZE;
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
        } else {
            int x = (int) ball.getX() - BALL_SIZE / 2;
            int y = (int) ball.getY() - BALL_SIZE / 2;
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
        }

        g.fillRect(paddle.getX() - PADDLE_WIDTH / 2, PANEL_HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
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

            if (isMenu || isPause) continue;


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
                if (Item.isItemAddable()) {
                    Item item = new Item(blocks[info.blockIndex()].getX() + BLOCK_WIDTH / 2 - ITEM_SIZE / 2,
                            blocks[info.blockIndex()].getY() + BLOCK_HEIGHT);
                    items.add(item);
                    this.add(item);
                }
            }

            paddle.adjustPaddleX(speed, useMouse, isPressed, isLeft);

            ball.adjustBallXY(speed);

            adjustItem();

            checkIsEnd();

            repaint();
        }
    }

    private void checkIsEnd() {
        if (ball.getY() > PANEL_HEIGHT + PADDLE_HEIGHT + PADDLE_BOTTOM) {
            isEnd = true;
            ball.setY(-1000);
            ball.setAngle(0);
            if (life == 1) gameInfoLabel.setVisible(true);
            lifePanel.setLifeText(--life);
        }
    }

    private void adjustItem() {
        for (int i = items.size() - 1; i >= 0; i--) {
            int y = items.get(i).getY() + ITEM_SPEED;

            if (items.get(i).isInItem(paddle.getX())) {
                this.remove(items.get(i));
                items.remove(i);
                lifePanel.setLifeText(++life);
                break;
            }

            if (y > PANEL_HEIGHT + PADDLE_HEIGHT + PADDLE_BOTTOM) {
                items.remove(i);
                break;
            }

            items.get(i).setY(y);
            items.get(i).setLocation(items.get(i).getX(), y);
        }
    }
}
