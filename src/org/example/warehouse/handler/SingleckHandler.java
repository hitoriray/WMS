package org.example.warehouse.handler;

import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.view.Inquire.ShowckView;
import org.example.warehouse.view.Inquire.SingleckView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SingleckHandler implements ActionListener {
    private SingleckView singleckView;

    public SingleckHandler(SingleckView singleckView) {
        this.singleckView = singleckView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = singleckView.getname();
        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "请输入货物名称!!!!", "查询", 2);
            return;
        }
        List<warehouseDao> list = ShowDataInformation.getckSingle(name);
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "抱歉，暂无该产品", "查询", 2);
        } else {
            new ShowckView(list);
        }
    }
}
