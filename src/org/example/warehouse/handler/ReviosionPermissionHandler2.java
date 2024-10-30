package org.example.warehouse.handler;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.service.RevisionPermissionService;
import org.example.warehouse.service.impl.RevisionServiceImpl;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.File.Permission.RevisionPermissionView;
import org.example.warehouse.view.File.Permission.ShowPermission;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReviosionPermissionHandler2 implements ActionListener {
    private RevisionPermissionView revisionPermissionView;
    private ShowPermission showPermission;
    private LoginView loginView;

    public ReviosionPermissionHandler2(RevisionPermissionView revisionPermissionView, ShowPermission showPermission) {
        this.revisionPermissionView = revisionPermissionView;
        this.showPermission = showPermission;
        this.loginView = showPermission.getLoginView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RevisionPermissionService revisionPermissionService = new RevisionServiceImpl();
        PermissionDao permissionDao = revisionPermissionView.getPermission();

        String name = loginView.getUserTxt().getText();
        PermissionDao curPermission = ShowDataInformation.getPermissionByName(name);  // 当前操作员的权限
        String canMofify = curPermission.getFile();
        System.out.println("curPermission: " + curPermission);
        System.out.println("canMofify: " + canMofify);
        if (canMofify != null && canMofify.equals("0")) {
            JOptionPane.showMessageDialog(null, "无修改权限！！！", "添加", 2);
            return;
        }

        boolean b = revisionPermissionService.RevisionPermission(permissionDao);
        if (b) {
            JOptionPane.showMessageDialog(null, "修改成功", "修改", 1);
            List<PermissionDao> list = ShowDataInformation.getPermissionInformation();
            new ShowPermission(list, loginView);
            revisionPermissionView.dispose();
            showPermission.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "修改失败", "修改", 2);
        }
    }
}
