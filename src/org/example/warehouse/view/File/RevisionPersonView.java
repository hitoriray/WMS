package org.example.warehouse.view.File;

import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.handler.RevisionPersonHandler;
import org.example.warehouse.service.UserService;
import org.example.warehouse.service.impl.UserServiceImpl;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RevisionPersonView extends JFrame {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel NameLabel = new JLabel("姓名：");
    JTextField NameTxt = new JTextField("请输入姓名");
    JLabel IDnumberLabel = new JLabel("身份证号：");
    JTextField IDnumberTxt = new JTextField();
    JLabel DataLabel = new JLabel("出生日期：");
    JTextField DataTxt = new JTextField("例如：2000-01-01");
    JLabel GenderLabel = new JLabel("性别：");
    JComboBox GenderJB = new JComboBox();

    String[] strArray1 = {"男", "女"};

    {
        GenderJB.addItem(strArray1[0]);
        GenderJB.addItem(strArray1[1]);
    }

    JLabel OriginLabel = new JLabel("籍贯：");
    JTextField OriginTxt = new JTextField("请输入籍贯");
    JLabel AddressLabel = new JLabel("家庭住址：");
    JTextField AddressTxt = new JTextField("请输入家庭住址");
    JRadioButton DoBtn = new JRadioButton("操作员");
    JLabel TypeLabel = new JLabel("类型：");
    JComboBox TypeJB = new JComboBox();
    String[] strArray2 = {"操作员", "管理员"};

    {
        TypeJB.addItem(strArray2[0]);
        TypeJB.addItem(strArray2[1]);
    }

    JLabel PhoneLabel = new JLabel("联系电话：");
    JTextField PhoneTxt = new JTextField("请输入联系电话");

    JButton RevisionBtn = new JButton("修改");
    RevisionPersonHandler revisionPersonHandler;

    public RevisionPersonView(LoginView loginView) {
        super("修改人员信息");
        revisionPersonHandler = new RevisionPersonHandler(this);
        Container contentPane = getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 20);
        Dimension dimension = new Dimension(150, 20);
        IDnumberLabel.setForeground(Color.RED);
        String name = loginView.getUserTxt().getText();
        UserService userService = new UserServiceImpl();
        String s = userService.selectID(name);
        IDnumberTxt.setText(s);
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
        RevisionBtn.setFont(font);

        addHintText(NameTxt, "请输入姓名");
        addHintText(IDnumberTxt, "请输入身份证号");
        addHintText(DataTxt, "例如：2000-01-01");
        addHintText(OriginTxt, "请输入籍贯");
        addHintText(AddressTxt, "请输入家庭住址");
        addHintText(PhoneTxt, "请输入联系电话");

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
        jPanel.add(RevisionBtn);
        IDnumberTxt.setEnabled(false);
        RevisionBtn.addActionListener(revisionPersonHandler);
        contentPane.add(jPanel);
        setSize(300, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    public UserTotalDao GetUser() {
        UserTotalDao userTotalDao = new UserTotalDao();
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

    private void addHintText(JTextField textField, String hint) {
        textField.setForeground(Color.GRAY);  // 初始提示词颜色
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);  // 输入时恢复正常字体颜色
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(hint);
                    textField.setForeground(Color.GRAY);  // 失去焦点后显示提示词
                }
            }
        });
    }
}
