package org.example.仓库管理系统.handler.ckHandler;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.service.RevisionckService;
import org.example.仓库管理系统.service.impl.RevisionckServiceImpl;
import org.example.仓库管理系统.view.Manager.RevisionckView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RevisionckHandler implements ActionListener {
    private RevisionckView revisionckView;
    public RevisionckHandler(RevisionckView revisionckView){
        this.revisionckView=revisionckView;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        RevisionckService reviosionckService = new RevisionckServiceImpl();
        ckDao ck =revisionckView.getck();
        if(revisionckView.getck().getId().equals("")||revisionckView.getck().getName().equals("")||revisionckView.getck().getType().equals("")||revisionckView.getck().getUnit().equals("")||revisionckView.getck().getRemark().equals("")){
            JOptionPane.showMessageDialog(null,"请输入完整信息！","修改",2);
            return;
        }
        boolean b = reviosionckService.Revisionck(ck);
        if (b) {
            JOptionPane.showMessageDialog(null, "修改成功", "修改", 1);
        } else {
            JOptionPane.showMessageDialog(null, "修改失败", "修改", 2);
        }

    }
}
