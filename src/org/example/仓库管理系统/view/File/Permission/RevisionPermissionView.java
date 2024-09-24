package org.example.仓库管理系统.view.File.Permission;

import org.example.仓库管理系统.dao.PermissionDao;
import org.example.仓库管理系统.handler.ReviosionPermissionHandler2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class RevisionPermissionView  extends  JFrame{
    JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,55,50));
    JLabel NameLabel = new JLabel("姓名：");
    JTextField NameTxt = new JTextField();
    JLabel IDLabel = new JLabel("身份证号：");
    JTextField IDTxt = new JTextField();
    int[] strArray = {0, 1};
    JLabel inquireLabel = new JLabel("查询权限:");
    JComboBox inquireJB = new JComboBox();

    {
        inquireJB.addItem(strArray[0]);
        inquireJB.addItem(strArray[1]);

    }
    JLabel inLabel = new JLabel("入库权限:");
    JComboBox inJB = new JComboBox();
    {
        inJB.addItem(strArray[0]);
        inJB.addItem(strArray[1]);

    }
    JLabel outLable = new JLabel("出库权限:");
    JComboBox outJB = new JComboBox();
    {
        outJB.addItem(strArray[0]);
        outJB.addItem(strArray[1]);

    }
    JLabel managerLabel = new JLabel("仓库管理权限:");
    JComboBox managerJB = new JComboBox();
    {
        managerJB.addItem(strArray[0]);
        managerJB.addItem(strArray[1]);

    }
    JLabel fileLabel = new JLabel("人员档案管理权限:");
    JComboBox fileJB = new JComboBox();
    {
        fileJB.addItem(strArray[0]);
        fileJB.addItem(strArray[1]);

    }

    JButton RevisionBtn = new JButton("修 改");
    ReviosionPermissionHandler2 reviosionPermissionHandler2;
    public RevisionPermissionView(String name,String IDnumber, ShowPermission showPermission){
        super("修改");
        reviosionPermissionHandler2 =new ReviosionPermissionHandler2(this,showPermission);
        Container contentPane=getContentPane();
        Font font = new Font("宋体", Font.PLAIN, 25);
        Dimension dimension = new Dimension(120, 25);
        NameLabel.setFont(font);

        NameTxt.setPreferredSize(dimension);
        NameTxt.setEnabled(false);
        IDLabel.setFont(font);
        IDTxt.setPreferredSize(dimension);
        IDTxt.setEnabled(false);
        inquireLabel.setFont(font);
        inLabel.setFont(font);
        outLable.setFont(font);
        managerLabel.setFont(font);
        fileLabel.setFont(font);
        RevisionBtn.setFont(new Font("宋体",Font.PLAIN,30));
        NameTxt.setText(name);
        IDTxt.setText(IDnumber);
        jPanel.add(NameLabel);
        jPanel.add(NameTxt);
        jPanel.add(IDLabel);
        jPanel.add(IDTxt);
        jPanel.add(inquireLabel);
        jPanel.add(inquireJB);
        jPanel.add(inLabel);
        jPanel.add(inJB);
        jPanel.add(outLable);
        jPanel.add(outJB);
        jPanel.add(managerLabel);
        jPanel.add(managerJB);
        jPanel.add(fileLabel);
        jPanel.add(fileJB);
        jPanel.add(RevisionBtn);
        RevisionBtn.addActionListener(reviosionPermissionHandler2);
        contentPane.add(jPanel);
        setSize(800,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public PermissionDao GetPermission(){
        PermissionDao permissionDao =new PermissionDao();
        permissionDao.setName(NameTxt.getText());
        permissionDao.setInquire(inquireJB.getSelectedItem().toString());
        permissionDao.setInbound(inJB.getSelectedItem().toString());
        permissionDao.setOutbound( outJB.getSelectedItem().toString());
        permissionDao.setManager(managerJB.getSelectedItem().toString());
        permissionDao.setFile(fileJB.getSelectedItem().toString());
        return permissionDao;
    }
}
