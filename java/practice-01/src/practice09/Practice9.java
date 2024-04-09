package practice09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Practice9 {

    public static void main(String[] args) {

//        new Practice1();
//        new Practice2();
//        new Practice3();
//        new Practice4();
//        new Practice5();
//        new Practice6();
//        new Practice7();
//        new Practice8();
        new Practice8_1();
    }

}

class Practice1 extends JFrame {

    public Practice1() throws HeadlessException {
        this.setTitle("Training01 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");
        label.setOpaque(true);
        label.setBackground(Color.yellow);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setText("사랑해");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setText("Love Java");
            }
        });

        container.add(label);

        this.setVisible(true);
    }
}

class Practice2 extends JFrame {

    public Practice2() throws HeadlessException {
        this.setTitle("Training02 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Waiting MouseEvent...");
        container.setBackground(Color.green);

        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getY());
                container.setBackground(Color.green);
                label.setText("Waiting MouseEvent...");
            }
        });

        container.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                container.setBackground(Color.yellow);
                label.setText("MouseDragged");
            }
        });

        container.add(label);

        this.setVisible(true);
    }
}

class Practice3 extends JFrame {

    private StringBuffer stringBuffer;

    public Practice3() {
        this.setTitle("Left 키로 문자열 교체");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        stringBuffer = new StringBuffer("Love Java");
        JLabel label = new JLabel(stringBuffer.toString());

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    label.setText(stringBuffer.reverse().toString());
                }
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice4 extends JFrame {

    public Practice4() {
        this.setTitle("Training 04 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    String labelText = label.getText();
                    label.setText(labelText.substring(1) + labelText.charAt(0));
                }
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice5 extends JFrame {

    private int size = 5;

    public Practice5() {
        this.setTitle("Training 05 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");
        label.setOpaque(true);
        label.setBackground(Color.yellow);
        label.setFont(new Font("Arial", Font.PLAIN, size));

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ADD) {
                    size += 5;
                    label.setFont(new Font("Arial", Font.PLAIN, size));
                } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    size = Math.max(size - 5, 5);
                    label.setFont(new Font("Arial", Font.PLAIN, size));
                }
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice6 extends JFrame {

    public Practice6() {
        this.setTitle("Training 06 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(null);

        JLabel label = new JLabel("C");
        label.setSize(10, 10);
        label.setLocation(100, 100);

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    label.setLocation((int) (Math.random() * 280), (int) (Math.random() * 260));
                }
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice7 extends JFrame {

    private int size = 20;

    public Practice7() {
        this.setTitle("Training 07 JLabel");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("Love Java");
        label.setOpaque(true);
        label.setBackground(Color.yellow);
        label.setFont(new Font("Arial", Font.PLAIN, size));

        label.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                size = (e.getWheelRotation() < 0) ? size + 1 : size - 1;
                label.setFont(new Font("Arial", Font.PLAIN, size));
            }
        });

        container.add(label);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }
}

class Practice8 extends JFrame {

    private boolean isPlay = true;
    private int playCount = 0;
    private boolean isCorrect;
    private int value;

    public Practice8() {
        this.setTitle("Open Challenge 10");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        container.setLayout(new GridLayout(2, 1));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));
        bottomPanel.setLayout(new GridBagLayout());

        JLabel[] labels = new JLabel[3];
        for (int i=0; i<3; i++) {
            labels[i] = randomLabel();
            topPanel.add(labels[i]);
        }
        JLabel info = new JLabel("시작합니다.");

        bottomPanel.add(info);

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                isCorrect = true;
                value = -1;

                if (e.getKeyCode() == KeyEvent.VK_ENTER && isPlay) {
                    playCount++;

                    for (JLabel label : labels) {
                        int randomValue = (int) (Math.random() * 5);
                        label.setText(String.valueOf(randomValue));

                        if (value == -1) value = randomValue;
                        else if (value != randomValue) isCorrect = false;
                    }

                    if (isCorrect) {
                        isPlay = false;
                        info.setText("축하합니다." + playCount + "번 만에 맞췄습니다.");
                    }
                    else info.setText("아쉽군요");
                }
            }
        });

        container.add(topPanel);
        container.add(bottomPanel);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }

    private JLabel randomLabel() {
        JLabel label = new JLabel(String.valueOf((int) (Math.random() * 5)));
        label.setPreferredSize(new Dimension(50, 50));
        label.setOpaque(true);
        label.setBackground(Color.pink);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    }
}

class Practice8_1 extends JFrame {

    static final String FILENAME = "/practice09.txt";
    private boolean isPlay = true;
    private int playCount = 0;
    private boolean isCorrect;
    private int value;

    public Practice8_1() {
        this.setTitle("Open Challenge 10");
        this.setSize(600, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        container.setLayout(new GridLayout(3, 1));
        topPanel.setLayout(new GridLayout(2, 1));
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));
        bottomPanel.setLayout(new GridBagLayout());

        // topPanel
        JPanel pathPanel = new JPanel();
        JPanel namePanel = new JPanel();

        JLabel pathLabel = new JLabel(new File("").getAbsolutePath());
        JButton pathButton = new JButton("경로 변경");
        pathButton.setFocusable(false);

        JLabel nameLabel = new JLabel("이름");
        JTextField nameField = new JTextField(16);

//        JButton openResultButton = new JButton("폴더 열기");
//        openResultButton.setFocusable(false);

        pathPanel.add(pathLabel);
        pathPanel.add(pathButton);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
//        namePanel.add(openResultButton);

        topPanel.add(pathPanel);
        topPanel.add(namePanel);

        // middlePanel
        JLabel[] labels = new JLabel[3];
        for (int i=0; i<3; i++) {
            labels[i] = randomLabel();
            middlePanel.add(labels[i]);
        }

        // bottomPanel
        JLabel info = new JLabel("시작합니다.");
        JButton restartButton = new JButton("다시하기");
        restartButton.setVisible(false);
        bottomPanel.add(info);
        bottomPanel.add(restartButton);

        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                isCorrect = true;
                value = -1;

                if (e.getKeyCode() == KeyEvent.VK_ENTER && isPlay) {
                    playCount++;
                    nameField.setFocusable(false);

                    for (JLabel label : labels) {
                        int randomValue = (int) (Math.random() * 5);
                        label.setText(String.valueOf(randomValue));

                        if (value == -1) value = randomValue;
                        else if (value != randomValue) isCorrect = false;
                    }

                    if (isCorrect) {
                        try {
                            File file = new File(pathLabel.getText() + FILENAME);

                            if (!file.exists()) file.createNewFile();

                            FileWriter fileWriter = new FileWriter(file, true);
                            fileWriter.write(String.format("이름 : %-10s, 시도 횟수 : %3d\n", nameField.getText(), playCount));
                            fileWriter.flush();


                        } catch (IOException exception) {
                            throw new RuntimeException(exception);
                        }


                        isPlay = false;
                        info.setText(nameField.getText() + "님 축하합니다." + playCount + "번 만에 맞췄습니다.");
                        restartButton.setVisible(true);
                    }
                    else info.setText("아쉽군요");
                }
            }
        });

        pathButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = fileChooser.showOpenDialog(container);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                pathLabel.setText(file.getAbsolutePath());
            }
        });

        nameField.addActionListener(e -> {
            nameField.setFocusable(false);
            nameField.setFocusable(true);
        });

        restartButton.addActionListener(e -> {
            isPlay = true;
            playCount = 0;
            nameField.setText("");
            info.setText("시작합니다.");
            nameField.setFocusable(true);
            restartButton.setVisible(false);
        });

//        openResultButton.addActionListener(e -> {
//            File file = new File(pathLabel.getText() + FILENAME);
//
//            if (!Desktop.isDesktopSupported()) info.setText("파일을 열 수 없습니다.");
//            else {
//                if (!file.exists()) info.setText("저장된 result가 없습니다.");
//                else {
//                    try {
//                        Desktop.getDesktop().open(file);
//                    } catch (IOException exception) {
//                        throw new RuntimeException(exception);
//                    }
//                }
//            }
//        });

        container.add(topPanel);
        container.add(middlePanel);
        container.add(bottomPanel);
        container.setFocusable(true);
        container.requestFocus();

        this.setVisible(true);
    }

    private JLabel randomLabel() {
        JLabel label = new JLabel(String.valueOf((int) (Math.random() * 5)));
        label.setPreferredSize(new Dimension(50, 50));
        label.setOpaque(true);
        label.setBackground(Color.pink);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    }

}