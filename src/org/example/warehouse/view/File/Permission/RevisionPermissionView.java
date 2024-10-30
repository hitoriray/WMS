package org.example.warehouse.view.File.Permission;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.handler.ReviosionPermissionHandler2;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class RevisionPermissionView extends JFrame {
    private LoginView loginView;
    private boolean isSuperAdmin;
    JPanel jPanel = new JPanel(new GridBagLayout()); // 改用 GridBagLayout 进行布局

    JLabel nameLabel = new JLabel("姓名：");
    JTextField nameTxt = new JTextField();
    JLabel idLabel = new JLabel("身份证号：");
    JTextField idTxt = new JTextField();

    JLabel inquireLabel = new JLabel("查询权限:");
    JComboBox<String> inquireJB = new JComboBox<>(new String[]{"0", "1"});
    JLabel inLabel = new JLabel("入库权限:");
    JComboBox<String> inJB = new JComboBox<>(new String[]{"0", "1"});
    JLabel outLabel = new JLabel("出库权限:");
    JComboBox<String> outJB = new JComboBox<>(new String[]{"0", "1"});
    JLabel managerLabel = new JLabel("仓库管理权限:");
    JComboBox<String> managerJB = new JComboBox<>(new String[]{"0", "1"});
    JLabel fileLabel = new JLabel("人员档案管理权限:");
    JComboBox<String> fileJB = new JComboBox<>(new String[]{"0", "1"});

    JButton revisionBtn = new JButton("修 改");
    ReviosionPermissionHandler2 reviosionPermissionHandler2;

    public RevisionPermissionView(String name, String IDnumber, String filePermission, ShowPermission showPermission) {
        super("修改权限");
        this.loginView = showPermission.getLoginView();
        String username = loginView.getUserTxt().getText().toString();
        this.isSuperAdmin = username.equalsIgnoreCase("ray");
        reviosionPermissionHandler2 = new ReviosionPermissionHandler2(this, showPermission);
        nameTxt.setText(name);
        nameTxt.setEnabled(false);
        idTxt.setText(IDnumber);
        idTxt.setEnabled(false);
        fileJB.setSelectedItem(filePermission); // 设置为原来的权限值

        // 设置超级管理员条件
        if (!isSuperAdmin) {
            fileJB.setEnabled(false); // 非超级管理员无法修改档案权限
        } else {
            if (name.equalsIgnoreCase("ray")) {
                inquireJB.setSelectedItem("1");
                inquireJB.setEnabled(false);
                inJB.setSelectedItem("1");
                inJB.setEnabled(false);
                outJB.setSelectedItem("1");
                outJB.setEnabled(false);
                managerJB.setSelectedItem("1");
                managerJB.setEnabled(false);
                fileJB.setSelectedItem("1");
                fileJB.setEnabled(false);
            } else {
                fileJB.setSelectedItem("1"); // 超级管理员可选
            }
        }

        // 布局设置
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(nameLabel, 0, 0, gbc);
        addComponent(nameTxt, 0, 1, gbc);
        addComponent(idLabel, 1, 0, gbc);
        addComponent(idTxt, 1, 1, gbc);
        addComponent(inquireLabel, 2, 0, gbc);
        addComponent(inquireJB, 2, 1, gbc);
        addComponent(inLabel, 3, 0, gbc);
        addComponent(inJB, 3, 1, gbc);
        addComponent(outLabel, 4, 0, gbc);
        addComponent(outJB, 4, 1, gbc);
        addComponent(managerLabel, 5, 0, gbc);
        addComponent(managerJB, 5, 1, gbc);
        addComponent(fileLabel, 6, 0, gbc);
        addComponent(fileJB, 6, 1, gbc);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addComponent(revisionBtn, 7, 0, gbc);

        revisionBtn.setFont(new Font("宋体", Font.BOLD, 22));
        revisionBtn.addActionListener(reviosionPermissionHandler2);

        getContentPane().add(jPanel);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addComponent(Component comp, int row, int col, GridBagConstraints gbc) {
        gbc.gridx = col;
        gbc.gridy = row;
        jPanel.add(comp, gbc);
    }

    public PermissionDao getPermission() {
        PermissionDao permissionDao = new PermissionDao();
        permissionDao.setName(nameTxt.getText());
        permissionDao.setIDnumber(idTxt.getText());
        permissionDao.setInquire((String) inquireJB.getSelectedItem());
        permissionDao.setInbound((String) inJB.getSelectedItem());
        permissionDao.setOutbound((String) outJB.getSelectedItem());
        permissionDao.setManager((String) managerJB.getSelectedItem());
        permissionDao.setFile((String) fileJB.getSelectedItem());
        return permissionDao;
    }
}
