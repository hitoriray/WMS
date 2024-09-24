package org.example.仓库管理系统.view.Person;

import org.example.仓库管理系统.handler.PersonHandler.PersonHandler;
import org.example.仓库管理系统.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class    PersonView extends JFrame {
    JPanel jPanel = new JPanel(new GridBagLayout());

    JLabel IDnumberLabel = new JLabel("身份证号：");
    JTextField IDnumberTxt = new JTextField();

    JLabel oldLabel = new JLabel("旧密码：");
    JTextField oldTxt = new JTextField();

    JLabel newLabel = new JLabel("新密码：");
    JTextField newTxt = new JTextField();

    JLabel sureLabel = new JLabel("新密码确认：");
    JTextField sureTxt = new JTextField();

    JButton RevisionBtn = new JButton("确认修改");
    JButton LinkBtn = new JButton("联系我们");
    PersonHandler personHandler;

    public JTextField getIDnumberTxt() {
        return IDnumberTxt;
    }

    public JTextField getOldTxt() {
        return oldTxt;
    }

    public JTextField getNewTxt() {
        return newTxt;
    }

    public JTextField getSureTxt() {
        return sureTxt;
    }

    public PersonView(LoginView loginView) {
        super("个人中心");
        personHandler = new PersonHandler(this, loginView);
        Container contentPane = getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 20);
        Dimension dimension = new Dimension(120, 20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;

        IDnumberLabel.setFont(font);
        IDnumberTxt.setPreferredSize(dimension);
        oldLabel.setFont(font);
        oldTxt.setPreferredSize(dimension);
        newLabel.setFont(font);
        newTxt.setPreferredSize(dimension);
        sureLabel.setFont(font);
        sureTxt.setPreferredSize(dimension);

        RevisionBtn.setFont(font);
        LinkBtn.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel.add(IDnumberLabel, gbc);

        gbc.gridy = 1;
        jPanel.add(oldLabel, gbc);

        gbc.gridy = 2;
        jPanel.add(newLabel, gbc);

        gbc.gridy = 3;
        jPanel.add(sureLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        jPanel.add(IDnumberTxt, gbc);

        gbc.gridy = 1;
        jPanel.add(oldTxt, gbc);

        gbc.gridy = 2;
        jPanel.add(newTxt, gbc);

        gbc.gridy = 3;
        jPanel.add(sureTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        jPanel.add(RevisionBtn, gbc);

        gbc.gridy = 5;
        jPanel.add(LinkBtn, gbc);

        RevisionBtn.addActionListener(personHandler);
        LinkBtn.addActionListener(personHandler);
        contentPane.add(jPanel);

        setSize(300, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}