package org.example.warehouse.view.File;
import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.handler.AddPersonHandler;

import javax.swing.*;
import java.awt.*;

public class AddPersonView extends JFrame {
    JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel NameLabel = new JLabel("姓名：");
    JTextField NameTxt = new JTextField();
    JLabel IDnumberLabel = new JLabel("身份证号：");
    JTextField IDnumberTxt = new JTextField();
    JLabel DataLabel = new JLabel("出生日期：");
    JTextField DataTxt = new JTextField();
    JLabel GenderLabel = new JLabel("性别：");
    JComboBox GenderJB = new JComboBox();

    String[] strArray1 = {"男", "女"};

    {
        GenderJB.addItem(strArray1[0]);
        GenderJB.addItem(strArray1[1]);
    }
    JLabel OriginLabel = new JLabel("籍贯：");
    JTextField OriginTxt = new JTextField();
    JLabel AddressLabel = new JLabel("家庭住址：");
    JTextField AddressTxt = new JTextField();
    JRadioButton DoBtn = new JRadioButton("操作员");
    JLabel TypeLabel = new JLabel("类型：");
    JComboBox TypeJB = new JComboBox();
    String[] strArray2 = {"操作员", "管理员"};
        {
        TypeJB.addItem(strArray2[0]);
            TypeJB.addItem(strArray2[1]);

    }

    JLabel PhoneLabel = new JLabel("联系电话：");
    JTextField PhoneTxt = new JTextField();

    JButton AdditionBtn = new JButton("添加");
    AddPersonHandler addPersonHandler;
    public AddPersonView(){
        super("添加");
        addPersonHandler =new AddPersonHandler(this);
        Container contentPane=getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 20);
        Dimension dimension = new Dimension(150, 20);
        NameLabel.setFont(font);
        NameTxt.setPreferredSize(dimension);
        IDnumberLabel.setFont(font);
        IDnumberTxt.setPreferredSize(dimension);
        DataLabel.setFont(font);
        DataTxt.setPreferredSize(dimension);
        GenderLabel.setFont(font);
        OriginLabel.setFont(font);
        OriginTxt.setPreferredSize(dimension);
        AddressLabel.setFont(font);
        AddressTxt.setPreferredSize(dimension);
        TypeLabel.setFont(font);
        PhoneLabel.setFont(font);
        PhoneTxt.setPreferredSize(dimension);
        AdditionBtn.setFont(font);

        jPanel.add(NameLabel);
        jPanel.add(NameTxt);
        jPanel.add(TypeLabel);
        jPanel.add(TypeJB);
        jPanel.add(GenderLabel);
        jPanel.add(GenderJB);
        jPanel.add(IDnumberLabel);
        jPanel.add(IDnumberTxt);
        jPanel.add(DataLabel);
        jPanel.add(DataTxt);
        jPanel.add(OriginLabel);
        jPanel.add(OriginTxt);
        jPanel.add(AddressLabel);
        jPanel.add(AddressTxt);
        jPanel.add(PhoneLabel);
        jPanel.add(PhoneTxt);
        jPanel.add(AdditionBtn);
        AdditionBtn.addActionListener(addPersonHandler);
        contentPane.add(jPanel);
        setSize(300,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }
    public UserTotalDao GetUser(){
        UserTotalDao userTotalDao =new UserTotalDao();
        userTotalDao.setName(NameTxt.getText());
        userTotalDao.setIDnumber(IDnumberTxt.getText());
        userTotalDao.setDate(DataTxt.getText());
        userTotalDao.setGender(GenderJB.getSelectedItem().toString());
        userTotalDao.setOrigin(OriginTxt.getText());
        userTotalDao.setAddress(AddressTxt.getText());
        userTotalDao.setType(TypeJB.getSelectedItem().toString());
        userTotalDao.setPhone(PhoneTxt.getText());
        return userTotalDao;
    }
}