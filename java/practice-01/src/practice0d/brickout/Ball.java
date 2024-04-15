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

        for (int i = 0; i < INITIAL_BLOCK_COUNT; i++) {
            int edge = findEdge(blocks[i].getX(), blocks[i].getY());
            if (isExists[i] && edge != 0) {
                if (remainingBlock == 1) {
                    return new AdjustGameInfo(true, -1, --remainingBlock);
                }
                setAngleBlockSide(edge);

                return new AdjustGameInfo(false, i, --remainingBlock);
            }
        }

        return new AdjustGameInfo(false, -1, remainingBlock);
    }

    private int findEdge(int blockX, int blockY) {
        double min = Double.MAX_VALUE;
        int[] index = {-1, -1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (min > length(this.x, this.y, blockX + BLOCK_WIDTH * i, blockY + BLOCK_HEIGHT * j)) {
                    min = length(this.x, this.y, blockX + BLOCK_WIDTH * i, blockY + BLOCK_HEIGHT * j);
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        if (min < BALL_SIZE / 2.0) {
//            return (Math.abs(this.x - (blockX + BLOCK_WIDTH * index[0])) > Math.abs(this.y - (blockY + BLOCK_HEIGHT * index[1])))
//                    ? 1 : -1;
            return -1;
        }

        if (Math.abs(this.y - (blockY + BLOCK_HEIGHT / 2.0)) < (BLOCK_HEIGHT + BALL_SIZE) / 2.0 &&
                this.x - BALL_SIZE / 2.0 > blockX && this.x + BALL_SIZE < blockX + BLOCK_WIDTH
        ) return -1;
        else if (Math.abs(this.x - (blockX + BLOCK_WIDTH / 2.0)) < (BLOCK_WIDTH + BALL_SIZE) / 2.0 &&
                this.y - BALL_SIZE / 2.0 > blockY && this.y + BALL_SIZE < blockY + BLOCK_HEIGHT
        ) return 1;
        else return 0;
    }

    private double length(double x1, double y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void adjustAngle(int paddleX, boolean isInPaddle) {
        if ((this.x - BALL_SIZE / 2.0 <= 0 && (Math.cos(Math.toRadians(this.getAngle())) < 0))
                || (this.x + BALL_SIZE / 2.0 >= PANEL_WIDTH && (Math.cos(Math.toRadians(this.angle)) > 0)))
            this.setAngle(180 - this.angle);
        if (this.y - BALL_SIZE / 2.0 <= 0 && (Math.sin(Math.toRadians(this.angle)) < 0)) this.setAngle(-this.angle);

        if (isInPaddle) {
            this.setAngle((int) (270 + 75 * (this.x - paddleX) * 2 / PADDLE_WIDTH) % 360);
            System.out.println(this.angle);
            this.setY(PANEL_HEIGHT - PADDLE_HEIGHT - BALL_SIZE / 2.0);
        }

        this.setAngle((360 + this.angle) % 360);
    }


    private void setAngleBlockSide(int side) {
        System.out.println(angle);
        if (side == 1) {
            this.setAngle((540 - this.angle) % 360);
            System.out.println(angle);
        } else {
            this.setAngle((360 - this.angle) % 360);
            System.out.println(angle);
        }
    }

    public double adjustSpeed(double speed, int remainingBlock) {
        if (remainingBlock % 10 == 0) return speed + 3.0;
        else return speed;
    }
}

record AdjustGameInfo(boolean clear, int blockIndex, int remainingBlock) {
}