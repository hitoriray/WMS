package org.example.warehouse.view.File;

import com.toedter.calendar.JDateChooser;
import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.handler.AddPersonHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPersonView extends JFrame {
    JPanel jPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 使用网格布局
    JLabel NameLabel = new JLabel("姓名：");
    JTextField NameTxt = new JTextField();
    JLabel IDnumberLabel = new JLabel("身份证号：");
    JTextField IDnumberTxt = new JTextField();
    JLabel DataLabel = new JLabel("出生日期：");
    JDateChooser DataChooser = new JDateChooser();
    JLabel GenderLabel = new JLabel("性别：");
    JComboBox<String> GenderJB = new JComboBox<>(new String[]{"男", "女"});
    JLabel OriginLabel = new JLabel("籍贯：");
    JTextField OriginTxt = new JTextField();
    JLabel AddressLabel = new JLabel("家庭住址：");
    JTextField AddressTxt = new JTextField();
    JLabel TypeLabel = new JLabel("类型：");
    JComboBox<String> TypeJB = new JComboBox<>(new String[]{"操作员", "管理员"});
    JLabel PhoneLabel = new JLabel("联系电话：");
    JTextField PhoneTxt = new JTextField();
    JButton AdditionBtn = new JButton("添加");
    AddPersonHandler addPersonHandler;

    public AddPersonView() {
        super("添加");
        addPersonHandler = new AddPersonHandler(this);
        Container contentPane = getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 20);
        Dimension dimension = new Dimension(200, 25); // 调整文本框的大小

        NameLabel.setFont(font);
        IDnumberLabel.setFont(font);
        DataLabel.setFont(font);
        GenderLabel.setFont(font);
        OriginLabel.setFont(font);
        AddressLabel.setFont(font);
        TypeLabel.setFont(font);
        PhoneLabel.setFont(font);
        AdditionBtn.setFont(font);

        NameTxt.setPreferredSize(dimension);
        IDnumberTxt.setPreferredSize(dimension);
        OriginTxt.setPreferredSize(dimension);
        AddressTxt.setPreferredSize(dimension);
        PhoneTxt.setPreferredSize(dimension);

        jPanel.add(NameLabel);
        jPanel.add(NameTxt);
        jPanel.add(IDnumberLabel);
        jPanel.add(IDnumberTxt);
        jPanel.add(DataLabel);
        jPanel.add(DataChooser);
        jPanel.add(GenderLabel);
        jPanel.add(GenderJB);
        jPanel.add(OriginLabel);
        jPanel.add(OriginTxt);
        jPanel.add(AddressLabel);
        jPanel.add(AddressTxt);
        jPanel.add(PhoneLabel);
        jPanel.add(PhoneTxt);
        jPanel.add(TypeLabel);
        jPanel.add(TypeJB);
        jPanel.add(new JLabel()); // 占位符
        jPanel.add(AdditionBtn);

        AdditionBtn.addActionListener(addPersonHandler);
        contentPane.add(jPanel, BorderLayout.CENTER);

        // 添加底部提示信息
        JLabel passwordHintLabel = new JLabel("初始密码为身份证后6位！");
        passwordHintLabel.setForeground(Color.RED);
        passwordHintLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(passwordHintLabel, BorderLayout.SOUTH);

        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public UserTotalDao GetUser() {
        UserTotalDao userTotalDao = new UserTotalDao();
        userTotalDao.setName(NameTxt.getText());
        userTotalDao.setIDnumber(IDnumberTxt.getText());
        Date selectedDate = DataChooser.getDate();
        if (selectedDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(selectedDate);
            userTotalDao.setDate(formattedDate);
        }
        userTotalDao.setGender(GenderJB.getSelectedItem().toString());
        userTotalDao.setOrigin(OriginTxt.getText());
        userTotalDao.setAddress(AddressTxt.getText());
        userTotalDao.setType(TypeJB.getSelectedItem().toString());
        userTotalDao.setPhone(PhoneTxt.getText());
        return userTotalDao;
    }

    private void addHintText(JTextField textField, String hint) {
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(hint);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
}