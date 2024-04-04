package Practice0A;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PracticeA {

    public static void main(String[] args) {

//        new Problem1();
//        new Problem2();
//        new Problem3();
//        new Problem4();
//        new Problem5();
//        new Problem6();
//        new Problem7();
//        new Problem8();
        new Problem9();
    }
}

class Problem1 extends JFrame {

    public Problem1() {
        this.setTitle("Problem1");
        this.setSize(300, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 1));

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        JCheckBox activateBox = new JCheckBox("버튼 비활성화");
        JCheckBox visibleBox = new JCheckBox(" 버튼 감추기");
        checkboxPanel.add(activateBox);
        checkboxPanel.add(visibleBox);

        JButton testButton = new JButton("test button");
        buttonPanel.add(testButton);

        activateBox.addItemListener(e -> testButton.setEnabled((e.getStateChange() != ItemEvent.SELECTED)));
        visibleBox.addItemListener(e -> testButton.setVisible((e.getStateChange() != ItemEvent.SELECTED)));


        add(checkboxPanel);
        add(buttonPanel);

        this.setVisible(true);
    }
}

class Problem2 extends JFrame {

    public Problem2() {
        this.setTitle("Problem2");
        this.setSize(480, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        JTextField nameField = new JTextField(10);
        JComboBox<String> nameBox = new JComboBox<>();

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    nameBox.addItem(nameField.getText());
                    nameField.setText("");
                }
            }
        });

        add(nameField);
        add(nameBox);

        this.setVisible(true);
    }
}

class Problem3 extends JFrame {

    private final int[] moneyList = new int[]{50000, 10000, 1000, 500, 100, 50, 10, 1};
    private final ArrayList<JTextField> moneyFields = new ArrayList<>();

    public Problem3() {
        this.setTitle("Practice3");
        this.setSize(480, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        JPanel inputPanel = new JPanel();
        JPanel moneyPanel = new JPanel();
        JLabel label;
        JTextField textField;

        String[] labelNameList = new String[]{"오만원", "만원", "천원", "500원", "100원", "50원", "10원", "1원"};

        container.setBackground(Color.pink);
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 16, 24));
        inputPanel.setBackground(Color.pink);
        moneyPanel.setLayout(new GridLayout(8, 2, 24, 8));
        moneyPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 160));
        moneyPanel.setBackground(Color.pink);

        label = new JLabel("금액");
        JTextField inputField = new JTextField(10);
        JButton inputButton = new JButton("계산");
        inputPanel.add(label);
        inputPanel.add(inputField);
        inputPanel.add(inputButton);

        for (String name : labelNameList) {

            moneyPanel.add(new JLabel(name, SwingConstants.RIGHT));

            textField = new JTextField("0", 5);
            textField.setFocusable(false);
            textField.setHorizontalAlignment(SwingConstants.CENTER);
            moneyFields.add(textField);
            moneyPanel.add(textField);
        }

        inputButton.addActionListener(e -> {
            int[] moneyValue = calculateValue(Integer.parseInt(inputField.getText()));

            for (int i = 0; i < moneyFields.size(); i++) {
                moneyFields.get(i).setText(String.valueOf(moneyValue[i]));
            }
        });

        container.add(inputPanel, BorderLayout.NORTH);
        container.add(moneyPanel, BorderLayout.CENTER);

        this.setVisible(true);

    }

    private int[] calculateValue(int money) {
        int[] result = new int[moneyList.length];

        for (int i = 0; i < moneyList.length; i++) {
            result[i] = money / moneyList[i];
            money %= moneyList[i];
        }

        return result;
    }
}

class Problem4 extends JFrame {

    private final int[] moneyList = new int[]{50000, 10000, 1000, 500, 100, 50, 10, 1};
    private final ArrayList<JTextField> moneyFields = new ArrayList<>();
    private final ArrayList<JCheckBox> moneyBoxes = new ArrayList<>();

    public Problem4() {
        this.setTitle("Practice3");
        this.setSize(480, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] labelNameList = new String[]{"오만원", "만원", "천원", "500원", "100원", "50원", "10원", "1원"};

        Container container = this.getContentPane();
        JPanel inputPanel = new JPanel();
        JPanel moneyPanel = new JPanel();
        JLabel label;
        JTextField textField;
        JCheckBox checkBox;

        container.setBackground(Color.pink);
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 16, 24));
        inputPanel.setBackground(Color.pink);
        moneyPanel.setLayout(new GridLayout(8, 3, 24, 8));
        moneyPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 160));
        moneyPanel.setBackground(Color.pink);

        label = new JLabel("금액");
        JTextField inputField = new JTextField(10);
        JButton inputButton = new JButton("계산");
        inputPanel.add(label);
        inputPanel.add(inputField);
        inputPanel.add(inputButton);

        for (String name : labelNameList) {
            textField = new JTextField("0", 5);
            textField.setFocusable(false);
            textField.setHorizontalAlignment(SwingConstants.CENTER);
            moneyFields.add(textField);

            checkBox = new JCheckBox("", true);
            moneyBoxes.add(checkBox);

            moneyPanel.add(checkBox);
            moneyPanel.add(new JLabel(name, SwingConstants.RIGHT));
            moneyPanel.add(textField);
        }

        inputButton.addActionListener(e -> {
            int[] moneyValue = calculateValue(Integer.parseInt(inputField.getText()));

            for (int i = 0; i < moneyFields.size(); i++) {
                moneyFields.get(i).setText(String.valueOf(moneyValue[i]));
            }
        });

        container.add(inputPanel, BorderLayout.NORTH);
        container.add(moneyPanel, BorderLayout.CENTER);

        this.setVisible(true);

    }

    private int[] calculateValue(int money) {
        int[] result = new int[moneyList.length];

        for (int i = 0; i < moneyList.length; i++) {
            if (moneyBoxes.get(i).isSelected()) {
                result[i] = money / moneyList[i];
                money %= moneyList[i];
            }
        }

        if (money != 0) {
            Arrays.fill(result, -1);
        }

        return result;
    }
}

class Problem5 extends JFrame {

    public Problem5() {
        this.setTitle("Problem5");
        this.setSize(300, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 100, 200, 150);
        JLabel valueLabel = new JLabel("150");
        valueLabel.setOpaque(true);
        valueLabel.setBackground(Color.green);

        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.addChangeListener(e -> valueLabel.setText(String.valueOf(slider.getValue())));

        add(slider);
        add(valueLabel);
        this.setVisible(true);
    }
}

class Problem6 extends JFrame {

    public Problem6() {
        this.setTitle("Problem6");
        this.setSize(600, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 1));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 16);
        JLabel valueLabel = new JLabel("I Love Java");
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.addChangeListener(e -> valueLabel.setFont(new Font("맑은 고딕", Font.BOLD, slider.getValue())));


        add(slider);
        add(valueLabel);
        this.setVisible(true);
    }
}

class Problem7 extends JFrame {

    private int selectedIndex = 0;

    public Problem7() {
        this.setTitle("Problem7");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String path = "src/Practice0A/fruit/images/";
        String[] imagePath = new String[]{"apple", "avocado", "banana", "cherries", "dragon-fruit", "grape", "lemon", "mango", "orange", "watermelon"};

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridBagLayout());

        ButtonGroup radioGroup = new ButtonGroup();
        JRadioButton leftRadio = new JRadioButton("left");
        JRadioButton rightRadio = new JRadioButton("right");
        radioGroup.add(leftRadio);
        radioGroup.add(rightRadio);
        buttonPanel.add(leftRadio);
        buttonPanel.add(rightRadio);

        JLabel imageLabel = new JLabel();
        ImageIcon leftImage = resizeIcon(path + imagePath[selectedIndex] + ".png", 200, 200);
        imageLabel.setIcon(leftImage);
        imagePanel.add(imageLabel);

        add(buttonPanel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (leftRadio.isSelected()) selectedIndex = (10 + selectedIndex - 1) % imagePath.length;
                else if (rightRadio.isSelected()) selectedIndex = (selectedIndex + 1) % 10;
                else return;

                imageLabel.setIcon(resizeIcon(path + imagePath[selectedIndex] + ".png", 200, 200));
            }
        });

        this.setVisible(true);
    }

    private ImageIcon resizeIcon(String path, int width, int height) {
        Image image = new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }
}

class Problem8 extends JFrame {

    private final JLabel[] resultLabels = new JLabel[3];
    private static final String PATH = "src/Practice0A/images/";
    private static final String[] IMAGE_PATH = new String[] {"scissors.png", "rock.png", "paper.png"};

    public Problem8() {
        this.setTitle("Problem8");
        this.setSize(600, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.gray);
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultPanel.setBackground(Color.yellow);

        JButton[] gameButtons = new JButton[3];
        for (int i=0; i<3; i++) {
            gameButtons[i] = new JButton();
            gameButtons[i].setIcon(resizeIcon(PATH + IMAGE_PATH[i], 100, 100));

            int index = i;
            gameButtons[i].addActionListener(e -> runGame(index));

            buttonPanel.add(gameButtons[i]);
        }

        JLabel humanTextLabel = new JLabel("me");
        JLabel computerTextLabel = new JLabel("com");
        for (int i=0; i<3; i++) {
            resultLabels[i] = new JLabel();
        }
        resultLabels[2].setForeground(Color.red);

        resultPanel.add(resultLabels[0]);
        resultPanel.add(humanTextLabel);
        resultPanel.add(resultLabels[1]);
        resultPanel.add(computerTextLabel);
        resultPanel.add(resultLabels[2]);

        add(buttonPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void runGame(int index) {
        int computer = (int) (Math.random() * 3);
        String result;

        if ((3 + index - computer) % 3 == 0) result = "Draw !!!";
        else if ((3 + index - computer) % 3 == 1) result = "Me !!!";
        else result = "Computer !!!";

        resultLabels[0].setIcon(resizeIcon(PATH + IMAGE_PATH[index], 100, 100));
        resultLabels[1].setIcon(resizeIcon(PATH + IMAGE_PATH[computer], 100, 100));
        resultLabels[2].setText(result);
    }

    private ImageIcon resizeIcon(String path, int width, int height) {
        Image image = new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }
}

class Problem9 extends JFrame {

    private int clickNum = 9;
    private long startTime;
    private final JLabel[] labels = new JLabel[10];

    public Problem9() {
        int width = 300;
        int height = 300;
        this.setTitle("Problem9");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        startTime = System.currentTimeMillis();

        JLabel resultLabel = new JLabel("성공");
        JButton retryButton = new JButton("다시하기");
        resultLabel.setLocation(width / 2 - 24, height / 2 - 70);
        resultLabel.setSize(100, 20);
        retryButton.setLocation(width / 2 - 30, height / 2 - 20);
        retryButton.setSize(60, 40);
        resultLabel.setVisible(false);
        retryButton.setVisible(false);
        add(resultLabel);
        add(retryButton);

        for (int i=0; i<10; i++) {
            labels[i] = new JLabel();
            labels[i].setText(String.valueOf(i));
            labels[i].setForeground(Color.magenta);
            labels[i].setSize(16, 16);
            labels[i].setLocation((int) (Math.random() * (width - 20)), (int) (Math.random() * (height - 40)));
            add(labels[i]);

            int index = i;
            labels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (clickNum == index) {
                        labels[index].setVisible(false);

                        if (index == 0) {
                            long resultMs = System.currentTimeMillis() - startTime;
                            resultLabel.setText(String.format("%.3f초",resultMs / (double) 1000));
                            resultLabel.setVisible(true);
                            retryButton.setVisible(true);
                        }
                    }

                    clickNum--;
                }
            });
        }

        retryButton.addActionListener(e -> {
            clickNum = 9;
            startTime = System.currentTimeMillis();

            resultLabel.setVisible(false);
            retryButton.setVisible(false);
            for (JLabel l : labels) {
                l.setLocation((int) (Math.random() * (width - 20)), (int) (Math.random() * (height - 40)));
                l.setVisible(true);
            };
        });

        this.setVisible(true);
    }
}