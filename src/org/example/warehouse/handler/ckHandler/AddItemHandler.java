package org.example.warehouse.handler.ckHandler;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.AddItemService;
import org.example.warehouse.service.impl.AddItemServiceImpl;
import org.example.warehouse.view.Manager.AddItemView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemHandler implements ActionListener {
    private AddItemView addItemView;

    public AddItemHandler(AddItemView addItemView) {
        this.addItemView = addItemView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("添加")) {
            AddItemService addItemService = new AddItemServiceImpl();
            warehouseDao ck = addItemView.Getck();
            if (ck.getId().equals("") || ck.getName().equals("") || ck.getType().equals("") || ck.getUnit().equals("") || ck.getRemark().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入完整信息！", "添加", 2);
                return;
            }
            boolean ok = addItemService.addItem(ck);
            if (ok) {
                JOptionPane.showMessageDialog(null, "添加成功", "添加", 1);
            } else {
                JOptionPane.showMessageDialog(null, "添加失败", "添加", 2);
            }
        }
    }
}
