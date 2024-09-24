package org.example.warehouse.handler.inquireHandler;

import org.example.warehouse.dao.PermissionDao;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.File.Permission.PermissionView;
import org.example.warehouse.view.File.Permission.ShowPermission;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InquirePermisionHandler implements ActionListener {
    private PermissionView permissionView;

    public InquirePermisionHandler(PermissionView permissionView) {
        this.permissionView = permissionView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<PermissionDao> list = ShowDataInformation.getPermissionInformation();
        new ShowPermission(list);
    }
}
