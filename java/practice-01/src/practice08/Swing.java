package practice08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Swing {
    public static void main(String[] args) {

        new MyFrame();

    }
}

class MyFrame extends JFrame implements ActionListener, KeyListener {

    private final JTextField inputName;
    private final DefaultListModel<String> model;
    private final JList jList;

    //생성자
    public MyFrame() {
        setSize(800, 500);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JLabel label = new JLabel("추가할 이름입력");
        inputName = new JTextField(10);
        JButton addBtn = new JButton("추가");
        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);

        JButton deleteBtn = new JButton("삭제");
        deleteBtn.setActionCommand("delete");
        deleteBtn.addActionListener(this);


        JPanel topPanel = new JPanel();
        topPanel.add(label);
        topPanel.add(inputName);
        topPanel.add(addBtn);
        topPanel.add(deleteBtn);

        add(topPanel, BorderLayout.NORTH);


        jList = new JList<>();
        //JList 에 출력할 데이터를 가지고 있는 모델 객체
        model = new DefaultListModel<>();
        model.addElement("안녕하세요");
        model.addElement("자바");
        model.addElement("어려워요");
        //모델을 JList 에 연결하기
        jList.setModel(model);

        //스크롤 페널에 JList 넣어주기
        JScrollPane sc = new JScrollPane(jList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //프레임에 스크롤 페널 추가하기
        add(sc, BorderLayout.CENTER);

        //JTextField 에 키 리스너 등록하기
        inputName.addKeyListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("add")) {
            String name = inputName.getText();
            model.addElement(name);
            inputName.setText("");
        } else if (command.equals("delete")) {
            int index = jList.getSelectedIndex();
            if (index >= 0) {
                model.remove(index);
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ENTER) {//만일 엔터키를 눌렀다면

            String name = inputName.getText();
            model.addElement(name);
            inputName.setText("");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("keyReleased");
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("keyTyped");
    }
}
