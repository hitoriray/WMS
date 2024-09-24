package org.example.仓库管理系统.handler.ckHandler;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.service.impl.ShowDataInformation;
import org.example.仓库管理系统.view.Inquire.ShowckView1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteckHandler implements ActionListener {
    private ShowckView1 showckView1;
    public DeleteckHandler(ShowckView1 showckView1){
        this.showckView1=showckView1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean s = ShowDataInformation.deleteck(showckView1.getTxt());
        if(s){

            JOptionPane.showMessageDialog(null,"删除成功","删除",1);
            List<ckDao> list = ShowDataInformation.getck();
            new ShowckView1(list);
            showckView1.dispose();

        }else{
            JOptionPane.showMessageDialog(null,"删除失败","删除",2);
        }

    }
}
