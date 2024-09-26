package org.example.warehouse.handler.ckHandler;

import org.example.warehouse.dao.ckDao;
import org.example.warehouse.service.AddckService;
import org.example.warehouse.service.impl.AddckServiceImpl;
import org.example.warehouse.view.Manager.AddckView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddckHandler implements ActionListener {
    private AddckView addckView;

    public AddckHandler(AddckView addckView) {
        this.addckView = addckView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("添加")) {
            AddckService addckService = new AddckServiceImpl();
            ckDao ck = addckView.Getck();
            if (ck.getId().equals("") || ck.getName().equals("") || ck.getType().equals("") || ck.getUnit().equals("") || ck.getRemark().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入完整信息！", "添加", 2);
                return;
            }
            boolean ok = addckService.addck(ck);
            if (ok) {
                JOptionPane.showMessageDialog(null, "添加成功", "添加", 1);
            } else {
                JOptionPane.showMessageDialog(null, "添加失败", "添加", 2);
            }
        }
    }
}
