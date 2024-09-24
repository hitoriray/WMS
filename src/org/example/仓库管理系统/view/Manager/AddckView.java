package org.example.仓库管理系统.view.Manager;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.handler.ckHandler.AddckHandler;
import org.example.仓库管理系统.service.impl.ShowDataInformation;

import javax.swing.*;
import java.awt.*;

public class AddckView  extends JFrame {
    JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel IdLabel = new JLabel("货物编号：");
    JTextField IdTxt = new JTextField();
    JLabel NameLabel = new JLabel("货物名称：");
    JTextField NameTxt = new JTextField();
    JLabel TypeLabel = new JLabel("货物类型：");
    JTextField TypeTxt = new JTextField();
    JLabel UnitLabel = new JLabel("计量单位：");
    JComboBox UnitJB = new JComboBox();

    String[] strArray1 = {"件","套","公斤","吨","升","米","毫米","个"};

    {
        UnitJB.addItem(strArray1[0]);
        UnitJB.addItem(strArray1[1]);
        UnitJB.addItem(strArray1[2]);
        UnitJB.addItem(strArray1[3]);
        UnitJB.addItem(strArray1[4]);
        UnitJB.addItem(strArray1[5]);
        UnitJB.addItem(strArray1[6]);
        UnitJB.addItem(strArray1[7]);

    }
    JLabel RemarkLabel = new JLabel("     备注：");
    JTextField RemarkTxt = new JTextField();

    JButton AdditionBtn = new JButton("添加");
    AddckHandler addckHandler;
    public AddckView(){
        super("添加货物");
        ShowDataInformation showDataInformation =new ShowDataInformation();
        String getid = showDataInformation.getid();

        IdTxt.setText(String.valueOf(Integer.parseInt(getid)+1));
        IdTxt.setEnabled(false);
        addckHandler =new AddckHandler(this);
        Container contentPane=getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 20);
        Dimension dimension = new Dimension(150, 20);
        IdLabel.setFont(font);
        IdTxt.setPreferredSize(dimension);
        NameLabel.setFont(font);
        NameTxt.setPreferredSize(dimension);
        TypeLabel.setFont(font);
        TypeTxt.setPreferredSize(dimension);
        UnitLabel.setFont(font);
        RemarkLabel.setFont(font);
        RemarkTxt.setPreferredSize(dimension);


        AdditionBtn.setFont(font);


        jPanel.add(IdLabel);
        jPanel.add(IdTxt);
        jPanel.add(NameLabel);
        jPanel.add(NameTxt);
        jPanel.add(TypeLabel);
        jPanel.add(TypeTxt);
        jPanel.add(UnitLabel);
        UnitJB.setEditable(true);
        jPanel.add(UnitJB);
        jPanel.add(RemarkLabel);
        jPanel.add(RemarkTxt);

        jPanel.add(AdditionBtn);
        AdditionBtn.addActionListener(addckHandler);
        contentPane.add(jPanel);
        setSize(300,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }
    public ckDao Getck(){
        ckDao ck =new ckDao();
        ck.setId(IdTxt.getText());
        ck.setName(NameTxt.getText());
        ck.setType(TypeTxt.getText());
        ck.setUnit(UnitJB.getSelectedItem().toString());
        ck.setRemark(RemarkTxt.getText());
        return ck;
    }

    public static void main(String[] args) {
        new AddckView();
    }


    }

