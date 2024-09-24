package org.example.仓库管理系统.handler;

import org.example.仓库管理系统.dao.PermissionDao;
import org.example.仓库管理系统.service.impl.ShowDataInformation;
import org.example.仓库管理系统.view.File.Permission.RevisionPermissionView;
import org.example.仓库管理系统.view.File.Permission.ShowPermission;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReviosionPermissionHandler1 implements ActionListener {
    private ShowPermission showPermission;

    public ReviosionPermissionHandler1(ShowPermission showPermission){
        this.showPermission=showPermission;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton) e.getSource();
        String text= jButton.getText();
             if(text.equals("查询人员")){
            String s = showPermission.getInformation();
            List<PermissionDao> list = ShowDataInformation.getPermissionInformation1(s);
            new ShowPermission(list);
        }

    }
}

