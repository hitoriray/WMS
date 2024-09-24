package org.example.warehouse.handler;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.File.Permission.ShowPermission;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReviosionPermissionHandler1 implements ActionListener {
    private ShowPermission showPermission;

    public ReviosionPermissionHandler1(ShowPermission showPermission) {
        this.showPermission = showPermission;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("查询人员")) {
            String s = showPermission.getInformation();
            List<PermissionDao> list = ShowDataInformation.getPermissionInformation1(s);
            new ShowPermission(list);
        }
    }
}

