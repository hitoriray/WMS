package org.example.warehouse.handler;

import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.service.RevisionService;
import org.example.warehouse.service.UserService;
import org.example.warehouse.service.impl.ReviosionPersonImpl;
import org.example.warehouse.service.impl.UserServiceImpl;
import org.example.warehouse.view.File.RevisionPersonView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RevisionPersonHandler extends KeyAdapter implements ActionListener {
    private RevisionPersonView revisionPersonView;


    public RevisionPersonHandler(RevisionPersonView revisionPersonView) {
        this.revisionPersonView = revisionPersonView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("修改")) {
            RevisionService revisionService = new ReviosionPersonImpl();
            UserTotalDao userTotalDao = revisionPersonView.GetUser();
            String iDnumber = userTotalDao.getIDnumber();
            String name = userTotalDao.getName();
            String date = userTotalDao.getDate();
            String origin = userTotalDao.getOrigin();
            String address = userTotalDao.getAddress();
            String phone = userTotalDao.getPhone();
            if (name.equals("") || iDnumber.equals("") || date.equals("") || origin.equals("") || address.equals("") || phone.equals("")) {
                JOptionPane.showMessageDialog(null, "请把信息填写完整！！！", "添加", 2);
                return;
            }
            UserService userService = new UserServiceImpl();
            boolean yanzhengname = userService.yanzhengname(name);
            if ((yanzhengname)) {
            } else {
                JOptionPane.showMessageDialog(null, "输入人员不存在！", "修改", 2);
                return;
            }
            Pattern p1 = Pattern.compile("(\\d{17}[0-9a-zA-Z])|(\\d{14}[0-9a-zA-Z])");
            Matcher matcher = p1.matcher(iDnumber);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "身份证号不合法", "修改", 2);
            } else {
                boolean yanzhengrevision = revisionService.yangzhenRevision(userTotalDao);
                if (yanzhengrevision) {
                    JOptionPane.showMessageDialog(null, "修改成功", "修改", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "出生日期不合规", "修改", 2);
                }
            }
        }
    }
}