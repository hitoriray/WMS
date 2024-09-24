package org.example.warehouse.handler.ckHandler;

import org.example.warehouse.dao.ckDao;
import org.example.warehouse.service.RevisionckService;
import org.example.warehouse.service.impl.RevisionckServiceImpl;
import org.example.warehouse.view.Manager.RevisionSetupView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupHandler implements ActionListener {
    private RevisionSetupView revisionSetupView;

    public SetupHandler(RevisionSetupView revisionSetupView) {
        this.revisionSetupView = revisionSetupView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ckDao ck = revisionSetupView.getSetup();
        RevisionckService revisionckService = new RevisionckServiceImpl();
        boolean b = revisionckService.revisionsetup(ck);
        if (b) {
            JOptionPane.showMessageDialog(null, "修改成功", "修改", 1);
        } else {
            JOptionPane.showMessageDialog(null, "你输入的信息有误，请重新输入", "修改", 1);
        }
    }
}
