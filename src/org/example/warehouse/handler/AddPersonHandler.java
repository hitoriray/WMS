package org.example.warehouse.handler;

import org.example.warehouse.dao.UserTotalDao;
import org.example.warehouse.service.MainUserService;
import org.example.warehouse.service.impl.AddUserImpl;
import org.example.warehouse.view.File.AddPersonView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPersonHandler extends KeyAdapter implements ActionListener {
    private AddPersonView addPersonView;

    public AddPersonHandler(AddPersonView addPersonView) {
        this.addPersonView = addPersonView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("添加")) {
            MainUserService mainUserService = new AddUserImpl();
            UserTotalDao userTotalDao = addPersonView.GetUser();
            String name = userTotalDao.getName();
            String iDnumber = userTotalDao.getIDnumber();
            String date = userTotalDao.getDate();
            String origin = userTotalDao.getOrigin();
            String address = userTotalDao.getAddress();
            String phone = userTotalDao.getPhone();
            if (name.equals("") || iDnumber.equals("") || date.equals("") || origin.equals("") || address.equals("") || phone.equals("")) {
                JOptionPane.showMessageDialog(null, "请把信息填写完整！！！", "添加", 2);
                return;
            }

            Pattern p1 = Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
            Matcher matcher = p1.matcher(iDnumber);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "身份证号不合法", "添加", 2);
                return;
            }
            if (!check(date)) {
                JOptionPane.showMessageDialog(null, "出生日期不合规", "添加", 2);
            } else {
                boolean ok = mainUserService.verifyAdd(userTotalDao);
                if (ok) {
                    JOptionPane.showMessageDialog(null, "添加成功", "添加", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "添加人员重复！", "添加", 2);
                }

            }
        }
    }

    static boolean check(String str) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sd.setLenient(false);
            sd.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
