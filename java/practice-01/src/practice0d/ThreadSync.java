package practice0d;

public class ThreadSync {
    public static void main(String[] args) {
        Board board = new Board();
        StudentThread studentThread01 = new StudentThread("name1", board);
        StudentThread studentThread02 = new StudentThread("name2", board);
        studentThread01.start();
        studentThread02.start();
    }
}

class Board {
    private int sum = 0;

    synchronized public void add() {
        int num = sum;
        num += 10;
        sum = num;
        System.out.println(Thread.currentThread().getName() + ": " + num + "==" + sum);
    }
}

class StudentThread extends Thread {
    private Board board;

    public StudentThread(String name, Board board) {
        super(name);
        this.board = board;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            board.add();
        }
    }
}