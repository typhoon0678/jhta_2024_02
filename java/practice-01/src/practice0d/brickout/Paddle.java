package practice0d.brickout;

import static practice0d.brickout.Constants.*;

public class Paddle {

    private int x = PANEL_WIDTH / 2; // Paddle 중심좌표 x

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void adjustPaddleX(double speed, boolean useMouse, boolean isPressed, boolean isLeft) {
        if (!useMouse && isPressed && isLeft) this.setX(this.x - (int) speed);
        else if (!useMouse && isPressed) this.setX(this.x + (int) speed);
    }

    public boolean isInPaddle(double ballX, double ballY) {
        return ballX >= this.x - PADDLE_WIDTH / 2.0 && ballX <= this.x + PADDLE_WIDTH / 2.0
                && ballY + BALL_SIZE / 2.0 >= PANEL_HEIGHT - PADDLE_HEIGHT && ballY - BALL_SIZE / 2.0 <= PANEL_HEIGHT;
    }
}
