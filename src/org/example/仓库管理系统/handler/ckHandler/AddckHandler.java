package org.example.仓库管理系统.handler.ckHandler;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.service.AddckService;
import org.example.仓库管理系统.service.impl.AddckServiceImpl;
import org.example.仓库管理系统.view.Manager.AddckView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddckHandler implements ActionListener {
    private AddckView addckView;
    public AddckHandler(AddckView addckView){
        this.addckView=addckView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("添加")) {
            AddckService addckService = new AddckServiceImpl();
            ckDao ck = addckView.Getck();
            if(addckView.Getck().getId().equals("")||addckView.Getck().getName().equals("")||addckView.Getck().getType().equals("")||addckView.Getck().getUnit().equals("")||addckView.Getck().getRemark().equals("")){
                JOptionPane.showMessageDialog(null,"请输入完整信息！","添加",2);
                return;
            }
            boolean addck = addckService.addck(ck);
            if (addck) {
                JOptionPane.showMessageDialog(null, "添加成功", "添加", 1);

            } else{
                JOptionPane.showMessageDialog(null, "添加失败", "添加", 2);
            }
        }

    }

}
