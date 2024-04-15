package practice0d.brickout;

import static practice0d.brickout.Constants.*;

public class Ball {
    private double x;
    private double y;
    private int angle;

    public Ball() {
        this.x = INITIAL_BALL_X;
        this.y = INITIAL_BALL_Y;
        this.angle = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void adjustBallXY(double speed) {
        this.setX(this.x + Math.cos(Math.toRadians(this.angle)) * speed);
        this.setY(this.y + Math.sin(Math.toRadians(angle)) * speed);
    }

    public AdjustGameInfo isInBlock(boolean[] isExists, Block[] blocks, int remainingBlock) {

        for (int i = 0; i < 40; i++) {
            if (isExists[i]
                    && this.x >= blocks[i].getX() && this.x <= blocks[i].getX() + BLOCK_WIDTH
                    && this.y >= blocks[i].getY() && this.y <= blocks[i].getY() + BLOCK_HEIGHT) {
                if (remainingBlock == 1) {
                    return new AdjustGameInfo(true, -1, --remainingBlock);
                }
                setAngleBlockSide(blocks[i].getX(), blocks[i].getY());

                return new AdjustGameInfo(false, i, --remainingBlock);
            }
        }

        return new AdjustGameInfo(false, -1, remainingBlock);
    }

    public void adjustAngle(int paddleX, boolean isInPaddle) {
        if ((this.x <= 0 && (Math.cos(Math.toRadians(this.getAngle())) < 0))
                || (this.x >= PANEL_WIDTH && (Math.cos(Math.toRadians(this.angle)) > 0)))
            this.setAngle(180 - this.angle);
        if (this.y <= 0 && (Math.sin(Math.toRadians(this.angle)) < 0)) this.setAngle(-this.angle);

        if (isInPaddle) {
            this.setAngle((int) (270 + 75 * (this.x - paddleX) * 2 / PADDLE_WIDTH) % 360);
            System.out.println(this.angle);
            this.setY(PANEL_HEIGHT - PADDLE_BOTTOM - PADDLE_HEIGHT);
        }

        this.setAngle((360 + this.angle) % 360);
    }


    private void setAngleBlockSide(int blockX, int blockY) {
        double left = this.x - blockX;
        double right = blockX + BLOCK_WIDTH - this.x;
        double top = this.y - blockY;
        double bottom = blockY + BLOCK_HEIGHT - this.y;

        if (Math.min(left, right) < Math.min(top, bottom)) this.setAngle((540 - this.angle) % 360);
        else this.setAngle((360 - this.angle) % 360);
    }

    public double adjustSpeed(double speed, int remainingBlock) {
        if (remainingBlock % 10 == 0) return speed + 3.0;
        else return speed;
    }
}

record AdjustGameInfo(boolean clear, int blockIndex, int remainingBlock) {
}