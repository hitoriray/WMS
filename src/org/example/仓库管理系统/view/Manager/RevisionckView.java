package org.example.仓库管理系统.view.Manager;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.handler.ckHandler.RevisionckHandler;

import javax.swing.*;
import java.awt.*;

public class RevisionckView extends JFrame {
    JPanel jPanel = new JPanel(new GridBagLayout());

    JLabel idLabel = new JLabel("货物编号：");
    JTextField idTxt = new JTextField();

    JLabel nameLabel = new JLabel("货物名称：");
    JTextField nameTxt = new JTextField();

    JLabel typeLabel = new JLabel("货物类型：");
    JTextField typeTxt = new JTextField();

    JLabel unitLabel = new JLabel("单位：");
    JTextField unitTxt = new JTextField();

    JLabel remarkLabel = new JLabel("备注：");
    JTextField remarkTxt = new JTextField();

    JButton btn = new JButton("确认");
    RevisionckHandler revisionckHandler;

    public RevisionckView(){
        super("修改货物信息");
        revisionckHandler = new RevisionckHandler(this);
        Container contentPane = getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 25);
        Dimension dimension = new Dimension(150, 25);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;

        idLabel.setFont(font);
        idTxt.setPreferredSize(dimension);
        nameLabel.setFont(font);
        nameTxt.setPreferredSize(dimension);
        typeLabel.setFont(font);
        typeTxt.setPreferredSize(dimension);
        unitLabel.setFont(font);
        unitTxt.setPreferredSize(dimension);
        remarkLabel.setFont(font);
        remarkTxt.setPreferredSize(dimension);
        btn.setFont(new Font("宋体", Font.PLAIN, 30));

        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel.add(idLabel, gbc);

        gbc.gridy = 1;
        jPanel.add(nameLabel, gbc);

        gbc.gridy = 2;
        jPanel.add(typeLabel, gbc);

        gbc.gridy = 3;
        jPanel.add(unitLabel, gbc);

        gbc.gridy = 4;
        jPanel.add(remarkLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        jPanel.add(idTxt, gbc);

        gbc.gridy = 1;
        jPanel.add(nameTxt, gbc);

        gbc.gridy = 2;
        jPanel.add(typeTxt, gbc);

        gbc.gridy = 3;
        jPanel.add(unitTxt, gbc);

        gbc.gridy = 4;
        jPanel.add(remarkTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        jPanel.add(btn, gbc);

        btn.addActionListener(revisionckHandler);
        contentPane.add(jPanel);

        setSize(420, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public ckDao getck(){
        ckDao ck = new ckDao();
        ck.setId(idTxt.getText());
        ck.setName(nameTxt.getText());
        ck.setType(typeTxt.getText());
        ck.setUnit(unitTxt.getText());
        ck.setRemark(remarkTxt.getText());
        return ck;
    }

    public static void main(String[] args) {
        new RevisionckView();
    }
}