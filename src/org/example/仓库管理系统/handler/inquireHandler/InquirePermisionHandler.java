package org.example.仓库管理系统.handler.inquireHandler;

import org.example.仓库管理系统.dao.PermissionDao;
import org.example.仓库管理系统.service.impl.ShowDataInformation;
import org.example.仓库管理系统.view.File.Permission.PermissionView;
import org.example.仓库管理系统.view.File.Permission.ShowPermission;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InquirePermisionHandler implements ActionListener {
    private PermissionView permissionView;
    public InquirePermisionHandler(PermissionView permissionView){
        this.permissionView=permissionView;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        List<PermissionDao> list = ShowDataInformation.getPermissionInformation();
        new ShowPermission(list);


    }
}
