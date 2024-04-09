package practice0c;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Flicking extends JFrame {

    public static void main(String[] args) {
        new Flicking();
    }

    public Flicking() {
        this.setTitle("flicking");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());

        String[] labelNames = {"AAA", "BBB", "CCC"};
        int[] milliseconds = {500, 0, 300};
        boolean[] isBackground = {true, false, true};

        for (int i = 0; i < 3; i++) {
            FlickingLabel label = new FlickingLabel(labelNames[i], milliseconds[i], isBackground[i]);
            this.add(label);
            new Thread(label).start();
        }

        for (int i = 0; i < 3; i++) {
            FlickingThread thread = new FlickingThread(labelNames[i], milliseconds[i], isBackground[i]);
            this.add(thread.getLabel());
            thread.start();
        }

        this.setVisible(true);
    }
}


class FlickingLabel extends JLabel implements Runnable {

    private final int delay;
    private final boolean isBackground;

    private final Color[] colors = new Color[]{Color.green, Color.yellow, null};

    public FlickingLabel(String labelName, int delay, boolean isBackground) {
        this.delay = delay;
        this.isBackground = isBackground;

        this.setText(labelName);
        this.setHorizontalAlignment(CENTER);
        this.setOpaque(true);
    }

    @Override
    public void run() {
        if (!isBackground) return;

        int index = 0;
        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.setBackground(colors[index]);
            index = (index + 1) % colors.length;
        }
    }

}

class FlickingThread extends Thread {

    private final int delay;
    private final boolean isBackground;
    private final JLabel label;

    private final Color[] colors = new Color[]{Color.green, Color.yellow, null};

    public FlickingThread(String labelName, int delay, boolean isBackground) {
        this.delay = delay;
        this.isBackground = isBackground;

        label  = new JLabel();
        label.setText(labelName);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
    }

    public JLabel getLabel() {
        return this.label;
    }

    @Override
    public void run() {
        if (!isBackground) return;

        int index = 0;
        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            label.setBackground(colors[index]);
            index = (index + 1) % colors.length;
        }
    }
}
