package practice0d.shooting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//상수들의 집합
enum GameState {
    LOADING, END, PLAY
}

public class GamePanel extends JPanel implements Runnable {
    //ImageIcon spaceShip = new ImageIcon("src/practice0d/images/space-ship.png");

    private boolean isLeft, isRight, isUp, isDown, isShoot;

    private int bgY = 0;
    private int point = 0;

    private GameState gameState = GameState.LOADING;

    private String monsters[] =
            {"monster01.png", "monster02.png", "monster03.png", "monster04.png"};

    int alienTimer = 0;

    Player player = new Player(
            new ImageIcon("src/practice0d/images/player.png"),
            GAME_WIDTH / 2 - 16,
            GAME_HEIGHT - 100
    );
    ImageIcon bg = new ImageIcon("src/practice0d/images/bg02.jpg");

    List<Bullet> bulletList = new ArrayList<>();
    List<Alien> alienList = new ArrayList<>();
    List<Boom> boomList = new ArrayList<>();

    List<Drug> drugList = new ArrayList<>();


    static int GAME_WIDTH = 800;
    static int GAME_HEIGHT = 600;

    Thread thread;

    Timer timer = new Timer(); // 스레드 구현한 거...
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            //System.out.println("timer task");
            makeAlien();
        }
    };

    Timer drugTimer = new Timer();
    TimerTask drugTimerTask = new TimerTask() {
        @Override
        public void run() {
            makeDrug();
        }
    };


    public GamePanel() {


        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    setLeft(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    setRight(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setUp(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setDown(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    setShoot(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //어떤 키든지 일단 떼면 전부다 false
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    setLeft(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    setRight(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setUp(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                    setDown(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (gameState == GameState.LOADING) {
                        thread = new Thread(GamePanel.this);
                        thread.start();
                        timer.schedule(timerTask, 1000, 500);
                        drugTimer.schedule(drugTimerTask, 10000, 8000);
                        gameState = GameState.PLAY;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    //setShoot(true);
                    if (gameState == GameState.END) {
                        player.setLife(3);
                        point = 0;
                        thread = new Thread(GamePanel.this);
                        thread.start();
                        timer = new Timer(); // 스레드 구현한 거...
                        timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                //System.out.println("timer task");
                                makeAlien();
                            }
                        };

                        drugTimer = new Timer();
                        drugTimerTask = new TimerTask() {
                            @Override
                            public void run() {
                                makeDrug();
                            }
                        };
                        timer.schedule(timerTask, 1000, 500);
                        drugTimer.schedule(drugTimerTask, 10000, 8000);
                        gameState = GameState.PLAY;
                        // 10초마다 한번씩 drug 떨어트려서 충돌하면 life 증가
                    }
                }
            }
        });
    }

    private void drawString(Graphics g, String str, Font font, int y) {
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int center = (GAME_WIDTH - fontMetrics.stringWidth(str)) / 2;
        g.drawString(str, center, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gameState == GameState.PLAY) {
            g.drawImage(bg.getImage(), 0, bgY, null);
            g.drawImage(bg.getImage(), 0, -853 + bgY, null);
            player.draw(g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("맑은 고딕", Font.BOLD, 18));
            g.drawString(Integer.toString(point), GAME_WIDTH - 100, 50);

            for (int i = 0; i < bulletList.size(); i++) {
                Bullet bullet = bulletList.get(i);
                bullet.draw(g);
            }
            for (int i = 0; i < alienList.size(); i++) {
                Alien alien = alienList.get(i);
                alien.draw(g);
            }
            for (int i = 0; i < boomList.size(); i++) {
                Boom boom = boomList.get(i);
                boom.draw(g);
            }
            for (int i = 0; i < drugList.size(); i++) {
                Drug drug = drugList.get(i);
                drug.draw(g);
            }
        } else if (gameState == GameState.END) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            g.setColor(Color.WHITE);
            String title = "SHOOTING GAME";
            Font font = new Font("맑은 고딕", Font.BOLD, 48);
            drawString(g, "SHOOTING GAME", font, 200);
            drawString(g, Integer.toString(point), font, 300);
            drawString(g, "RESTART PRESS R", font, 400);
        } else if (gameState == GameState.LOADING) {
            // 로딩화면 그리기....
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            g.drawImage(new ImageIcon("src/practice0d/images/bg.png").getImage(), 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
            g.setColor(Color.WHITE);
            Font font = new Font("맑은 고딕", Font.PLAIN, 36);
            drawString(g, "CATCH THE MONSTER", font, 80);
            ImageIcon loadingMonster = new ImageIcon("src/practice0d/images/loading-monster.png");
            g.drawImage(loadingMonster.getImage(),
                    (GAME_WIDTH - loadingMonster.getIconWidth()) / 2
                    , 150, null);
            font = new Font("맑은 고딕", Font.BOLD, 24);
            drawString(g, "LEFT/RIGHT/UP/DOWN ← → ↑ ↓", font, 450);
            drawString(g, "PRESS SPACE SHOOTING ", font, 480);
            drawString(g, "PRESS ENTER GAME START ", font, 510);
            drawString(g, "GOOD LUCK ", font, 540);

        }
    }

    private void makeAlien() {
        int random = (int) (Math.random() * monsters.length);
        Alien alien = new Alien(
                new ImageIcon("src/practice0d/images/" + monsters[random]), 3,
                (int) (Math.random() * (GAME_WIDTH - 100) + 50),
                -50
        );
        alien.setSpeedY((int) (Math.random() * 3 + 1));
        alienList.add(alien);
    }

    private void makeDrug() {
        System.out.println("drug 생성");
        Drug drug = new Drug(
                new ImageIcon("src/practice0d/images/medicines.png"),
                (int) (Math.random() * GAME_WIDTH - 100 + 50),
                -50
        );
        drug.setSpeedY((int) (Math.random() * 5 + 3));
        drugList.add(drug);
    }

    private void playerMove() {
        player.moveX(player.getSpeedX());
        player.moveY(player.getSpeedY());
    }

    private void alienMove() {
        //alien.moveY(5);
        for (int i = 0; i < alienList.size(); i++) {
            Alien alien = alienList.get(i);
            alien.moveY(alien.getSpeedY());

            Rectangle rectangle01 = new Rectangle(
                    player.getX(), player.getY(),
                    player.getWidth(), player.getHeight()

            );
            Rectangle rectangle02 = new Rectangle(
                    alien.getX(), alien.getY(),
                    alien.getWidth(), alien.getHeight()

            );
            if (hitObject(rectangle01, rectangle02)) {
                player.reduceLife();
                alienList.remove(alien);
                checkHeart();
            }

            if (alien.getY() > GAME_HEIGHT + 50) {
                alienList.remove(alien);
                player.reduceLife();
                checkHeart();
                //바닥에 닿은거
            }
        }
    }

    private void drugMove() {
        //alien.moveY(5);
        for (int i = 0; i < drugList.size(); i++) {
            Drug drug = drugList.get(i);
            drug.moveY(drug.getSpeedY());

            Rectangle rectangle01 = new Rectangle(
                    player.getX(), player.getY(),
                    player.getWidth(), player.getHeight()

            );
            Rectangle rectangle02 = new Rectangle(
                    drug.getX(), drug.getY(),
                    drug.getWidth(), drug.getHeight()

            );
            if (hitObject(rectangle01, rectangle02)) {
                player.addLife();
                drugList.remove(drug);
                checkHeart();
            }

            if (drug.getY() > GAME_HEIGHT + 50) {
                drugList.remove(drug);
            }
        }
    }

    // life가 끝나면 화면 나오게 하기..
    // 검은색 덮고 게임 점수 표시하고
    // 다시 할건지 말건지  r 누르면 다시 할 수 있게.....
    private void bulletMove() {
        for (int i = 0; i < bulletList.size(); i++) {
            //bullet 여러개
            Bullet bullet = bulletList.get(i);
            bullet.moveY(15);
            for (int j = 0; j < alienList.size(); j++) {
                Alien alien = alienList.get(j);
                Rectangle rectangle01 = new Rectangle(
                        bullet.getX(), bullet.getY(),
                        bullet.getWidth(), bullet.getHeight()

                );
                Rectangle rectangle02 = new Rectangle(
                        alien.getX(), alien.getY(),
                        alien.getWidth(), alien.getHeight()

                );
                if (hitObject(rectangle01, rectangle02)) {
                    bulletList.remove(bullet);
                    alien.reduceLife();
                    if (alien.getLife() <= 0) {
                        point += 100;
                        alienList.remove(alien);
                        Boom boom = new Boom(
                                new ImageIcon("src/practice0d/images/boom.png"),
                                alien.getX(),
                                alien.getY()
                        );
                        boomList.add(boom);
                        Timer boomTimer = new Timer();
                        TimerTask boomTimerTask = new TimerTask() {
                            @Override
                            public void run() {
                                boomList.remove(boom);
                            }
                        };
                        timer.schedule(boomTimerTask, 1000);
                    }

                    //코드 부탁함다...
                    //3초뒤에 없애 버리기...
                    //Timer
                }

            }
            if (bullet.getY() < -50) {
                bulletList.remove(bullet);
            }
        }
    }

    private void checkHeart() {
        if (player.getLife() <= 0) {
            gameState = GameState.END;
            thread.interrupt();
            bulletList.clear();
            alienList.clear();
            drugList.clear();
            boomList.clear();
            timer.cancel();
            drugTimer.cancel();
            //r 누르면 다시 시작
            //alienList비우기
            //bulletList비우기
        }
    }

    private void keyProcess() {
        if (isLeft()) {
            //player.setSpeedX(-5);
            player.moveX(-10);
        }
        if (isRight()) {
            //player.setSpeedX(5);
            player.moveX(10);
        }
        if (isUp()) {
            //player.setSpeedY(-5);
            player.moveY(-10);
        }
        if (isDown()) {
            //player.setSpeedY(+5);
            player.moveY(10);
        }
        if (isShoot()) {
            int bulletX = 0;
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    bulletX = player.getX() - 2;
                } else if (i == 2) {
                    bulletX = player.getX() + 26;
                } else {
                    bulletX = player.getX() + 12;
                }

                Bullet bullet = new Bullet(
                        new ImageIcon("src/practice0d/images/bullet01.png"),
                        bulletX,
                        player.getY() - 12
                );
                bulletList.add(bullet);
            }
            setShoot(false);
        }
    }

    @Override
    public void run() {
        while (true) {
            //실시간 실행코드 여기에 넣어주면 된다.
            keyProcess();
            playerMove();
            bulletMove();
            //makeAlien();
            alienMove();
            drugMove();

            //checkHeart();
            bgY += 1;
            if (bgY > 853) {
                bgY = 0;
            }
            //thanks to 형민.....
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                return;
                //throw new RuntimeException(e);
            }
        }
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public boolean isShoot() {
        return isShoot;
    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

    private boolean hitObject(Rectangle rectangle01,
                              Rectangle rectangle02) {
        return rectangle01.intersects(rectangle02);
    }
}