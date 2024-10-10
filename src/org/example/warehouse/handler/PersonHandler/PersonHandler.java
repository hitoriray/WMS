package org.example.warehouse.handler.PersonHandler;

import org.example.warehouse.service.RevisionService;
import org.example.warehouse.service.UserService;
import org.example.warehouse.service.impl.ReviosionPersonImpl;
import org.example.warehouse.service.impl.UserServiceImpl;
import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.Person.PersonView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonHandler implements ActionListener {
    private PersonView personView;
    private LoginView loginView;

    public PersonHandler(PersonView personView, LoginView loginView) {
        this.personView = personView;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = loginView.getUserTxt().getText();
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("确认修改")) {
            String text1 = personView.getIDnumberTxt().getText();
            String text2 = personView.getOldTxt().getText();
            String text3 = personView.getNewTxt().getText();
            String text4 = personView.getSureTxt().getText();
            if (text1.equals("") || text2.equals("") || text3.equals("") || text4.equals("")) {
                JOptionPane.showMessageDialog(null, "请填入完整信息！", "修改", 2);
                return;
            }
            UserService userService = new UserServiceImpl();
            boolean b = userService.verifyIDnumberByName(text1, name);
            if (b) {
                boolean c = userService.verifyPwd(text2);
                if (c) {
                    if (text2.equals(text3)) {
                        JOptionPane.showMessageDialog(null, "请设置新的密码！", "修改", 2);
                        return;
                    }
                    if (text3.equals(text4)) {
                        RevisionService revisionService = new ReviosionPersonImpl();
                        revisionService.modifyPwd(text2, text4);
                        JOptionPane.showMessageDialog(null, "修改成功", "修改", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "两次密码不一致！", "修改", 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误!!!", "修改", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "身份证号有误！", "修改", 2);
            }
        } else if (text.equals("联系我们")) {
            JOptionPane.showMessageDialog(null, "福建理工大学专线：95017（大陆地区） +86 571 95017（海外及港澳台地区）", "联系", 1);
        }
    }
}
