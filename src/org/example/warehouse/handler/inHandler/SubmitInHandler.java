package org.example.warehouse.handler.inHandler;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.AddItemService;
import org.example.warehouse.service.RevisionItemService;
import org.example.warehouse.service.impl.AddItemServiceImpl;
import org.example.warehouse.service.impl.RevisionItemServiceImpl;
import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.in.SimpleInView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubmitInHandler implements ActionListener {
    private SimpleInView simpleInView;
    private LoginView loginView;

    public SubmitInHandler(SimpleInView simpleView, LoginView loginView) {
        this.loginView = loginView;
        this.simpleInView = simpleView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("提交")) {
            String text1 = simpleInView.getIdTxt().getText();
            String text2 = simpleInView.getNumberField();
            if (text1.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入产品编号！！！！", "添加", 2);
                return;

            }
            RevisionItemService revisionItemService = new RevisionItemServiceImpl();
            warehouseDao ck = new warehouseDao();
            ck.setId(text1);
            ck.setInventory(text2);
            String flag = revisionItemService.revisionNumber(ck);
            if (flag == "1") {
                AddItemService addItemService = new AddItemServiceImpl();
                boundDao bo = new boundDao();
                int num1 = (int) (Math.random() * 10);
                int num2 = (int) (Math.random() * 10);
                int num3 = (int) (Math.random() * 10);
                int num4 = (int) (Math.random() * 10);
                String num = num1 + String.valueOf(num2) + num3 + num4;
                bo.setDanhao("IN_SimpleBound" + num);
                bo.setId(simpleInView.getIdTxt().getText());
                bo.setNumber(simpleInView.getNumberField());
                bo.setBoundtype("入库");
                bo.setName(loginView.getUserTxt().getText());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(formatter.format(date));
                bo.setTime(formatter.format(date));
                addItemService.addInbound(bo);
                JOptionPane.showMessageDialog(null, "入库成功!" + "您的入库编号为" + "IN_SimpleBound" + num, "入库", 1);

            } else if (flag == "-1") {
                JOptionPane.showMessageDialog(null, "抱歉，暂无该产品", "入库", 2);


            } else if (flag == "2") {
                JOptionPane.showMessageDialog(null, "您添加的数量过多，请重新输入", "入库", 2);


            } else if (flag == "3") {
                JOptionPane.showMessageDialog(null, "您添加的数量过少，请重新输入", "入库", 2);


            } else if (flag == "4") {
                JOptionPane.showMessageDialog(null, "请输入数量！！！！", "入库", 2);


            }
        } else if (text.equals("重置")) {
            simpleInView.getIdTxt().setText("");
        }

    }
}
