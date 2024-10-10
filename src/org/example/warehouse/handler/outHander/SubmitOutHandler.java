package org.example.warehouse.handler.outHander;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.AddItemService;
import org.example.warehouse.service.RevisionItemService;
import org.example.warehouse.service.impl.AddItemServiceImpl;
import org.example.warehouse.service.impl.RevisionItemServiceImpl;
import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.out.SimpleOutView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubmitOutHandler implements ActionListener {
    private SimpleOutView simpleOutView;
    private LoginView loginView;

    public SubmitOutHandler(SimpleOutView simpleOutView, LoginView loginView) {
        this.loginView = loginView;
        this.simpleOutView = simpleOutView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("提交")) {
            String text1 = simpleOutView.getIdTxt().getText();
            String text2 = simpleOutView.getNumberField();
            if (text1.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入产品编号！！！！", "添加", 2);
                return;
            }
            RevisionItemService revisionItemService = new RevisionItemServiceImpl();
            warehouseDao ck = new warehouseDao();
            ck.setId(text1);
            ck.setInventory(text2);
            String flag = revisionItemService.revisionNumber1(ck);
            if (flag == "1") {
                if (!simpleOutView.getCustomer().contains("@qq.com")) {
                    JOptionPane.showMessageDialog(null, " 请输入正确的邮箱信息!", "出库", 2);
                    return;
                }
                AddItemService addItemService = new AddItemServiceImpl();
                int num1 = (int) (Math.random() * 10);
                int num2 = (int) (Math.random() * 10);
                int num3 = (int) (Math.random() * 10);
                int num4 = (int) (Math.random() * 10);
                String num = num1 + String.valueOf(num2) + num3 + num4;
                boundDao bo = new boundDao();
                bo.setDanhao("OUT_SimpleBound" + num);
                bo.setId(simpleOutView.getIdTxt().getText());
                bo.setNumber(simpleOutView.getNumberField());
                bo.setBoundtype("出库");
                bo.setName(loginView.getUserTxt().getText());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(formatter.format(date));
                bo.setTime(formatter.format(date));
                addItemService.addInbound(bo);
                JOptionPane.showMessageDialog(null, " 出库成功!" + "您的出库编号为" + "OUT_SimpleBound" + num, "出库", 1);
            } else if (flag == "-1") {
                JOptionPane.showMessageDialog(null, "抱歉，暂无该产品", "添加", 2);
            } else if (flag == "2") {
                JOptionPane.showMessageDialog(null, "您出库的数量过多，请重新输入", "出库", 2);
            } else if (flag == "3") {
                JOptionPane.showMessageDialog(null, "您出库的数量过少，请重新输入", "出库", 2);
            } else if (flag == "4") {
                JOptionPane.showMessageDialog(null, "请输入数量！！！！", "出库", 2);
            } else if (flag == "5") {
                JOptionPane.showMessageDialog(null, "库存不够！", "出库", 2);
            }
        } else if (text.equals("重置")) {
            simpleOutView.getIdTxt().setText("");
        }
    }
}
