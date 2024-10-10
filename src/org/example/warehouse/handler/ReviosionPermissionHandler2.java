package org.example.warehouse.handler;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.service.RevisionPermissionService;
import org.example.warehouse.service.impl.RevisionServiceImpl;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.File.Permission.RevisionPermissionView;
import org.example.warehouse.view.File.Permission.ShowPermission;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReviosionPermissionHandler2 implements ActionListener {
    private RevisionPermissionView revisionPermissionView;
    private ShowPermission showPermission;

    public ReviosionPermissionHandler2(RevisionPermissionView revisionPermissionView, ShowPermission showPermission) {
        this.revisionPermissionView = revisionPermissionView;
        this.showPermission = showPermission;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RevisionPermissionService revisionPermissionService = new RevisionServiceImpl();
        PermissionDao permissionDao = revisionPermissionView.GetPermission();
        boolean b = revisionPermissionService.RevisionPermission(permissionDao);
        if (b) {
            JOptionPane.showMessageDialog(null, "修改成功", "修改", 1);
            List<PermissionDao> list = ShowDataInformation.getPermissionInformation();
            new ShowPermission(list);
            revisionPermissionView.dispose();
            showPermission.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "修改失败", "修改", 2);
        }
    }
}
