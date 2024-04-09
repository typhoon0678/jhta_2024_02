package practice0b.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class PaddlePanel extends JPanel implements Runnable {
    //   thread
    //   process(프로그램 하나 테트리스)   thread (노예 하나의 프로그램 (프로세스)안에서
    //   여러개의  일을 동시다발적 (열라 빨라서 마치 동시에 일어나는 것처럼 보인다. 컨텍스트 스위칭)으로 할 수 있다.)
    private int posX = 0;
    private int posY = 0;
    private int num = 0;
    private Thread thread;
    public String isDirection = "right";

    public PaddlePanel() {
        thread = new Thread(this);
        thread.start();
        this.setFocusable(true);
        this.requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(50 + posX, 200 + posY, 20, 20);
    }

    public void moveXY(String isDirection) {
        if (isDirection.equals("right")) posX += 2;
        if (isDirection.equals("left")) posX -= 2;
        if (isDirection.equals("up")) posY -= 2;
        if (isDirection.equals("down")) posY += 2;

        //상하 구현해보기...  다 한 사람 집에 가도 됨...
        repaint();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(10); // 0.1초동안 잠시 대기....
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //일해야할것들 여기다가 넣어주기...
            moveXY(isDirection);
            System.out.println(++num);
        }
    }
}

public class Paddle extends JFrame {
    PaddlePanel paddlePanel = new PaddlePanel();

    public Paddle() throws HeadlessException {
        this.setTitle("그리기");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paddlePanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    paddlePanel.isDirection ="right";
                    //paddlePanel.moveXY("right");
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    //paddlePanel.moveXY("left");
                    paddlePanel.isDirection ="left";
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    //paddlePanel.moveXY("up");
                    paddlePanel.isDirection ="up";
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    //paddlePanel.moveXY("down");
                    paddlePanel.isDirection ="down";
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.setContentPane(paddlePanel);
        this.setSize(300, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Paddle();
    }
}