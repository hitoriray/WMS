package org.example.warehouse.handler.inHandler;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;
import org.example.warehouse.service.AddBoundService;
import org.example.warehouse.service.RevisionItemService;
import org.example.warehouse.service.UserService;
import org.example.warehouse.service.impl.AddBoundServiceImpl;
import org.example.warehouse.service.impl.RevisionItemServiceImpl;
import org.example.warehouse.service.impl.ShowDataInformation;
import org.example.warehouse.service.impl.UserServiceImpl;
import org.example.warehouse.view.Inquire.ShowckView;
import org.example.warehouse.view.LoginView;
import org.example.warehouse.view.Report.ReportView;
import org.example.warehouse.view.in.MoreInView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoreInHandler implements ActionListener {
    private MoreInView moreInView;
    private LoginView loginView;
    private JTextField aTextField;
    private JTextField bTextField;

    private JTextField cTextField;
    private JTextField dTextField;
    private JTextField eTextField;


    public MoreInHandler(MoreInView moreInView, LoginView loginView) {
        this.moreInView = moreInView;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();

        if (text.equals("纭娣诲姞")) {
            int rows = moreInView.inventoryTable.getRowCount();

            // 鍒涘缓鏈嶅姟绫�
            UserService userService = new UserServiceImpl();
            warehouseDao ck = new warehouseDao();
            boundDao bo = new boundDao();
            AddBoundService addboundService = new AddBoundServiceImpl();

            boolean ok = false;
            for (int i = 0; i < rows; i++) {
                Boolean isSelected = (Boolean) moreInView.inventoryTable.getValueAt(i, 0); // 鑾峰彇澶嶉€夋鐘舵€�
                String quantity = (String) moreInView.inventoryTable.getValueAt(i, 8); // 鑾峰彇杈撳叆鐨勬暟閲�

                if (isSelected != null && isSelected) { // 濡傛灉澶嶉€夋琚€変腑
                    ok = true;
                    if (quantity == null || quantity.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "璇蜂负閫変腑鐨勮揣鐗╄緭鍏ユ暟閲忥紒", "鍏ュ簱", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (Integer.parseInt(quantity) <= 0) {
                        JOptionPane.showMessageDialog(null, "璐х墿杈撳叆鏁伴噺涓嶅悎娉曪紒", "鍏ュ簱", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }
            if (!ok) {
                JOptionPane.showMessageDialog(null, "璇烽€夋嫨闇€瑕佸叆搴撶殑鐗╂枡锛�", "鍏ュ簱", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (int i = 0; i < rows; i++) {
                Boolean isSelected = (Boolean) moreInView.inventoryTable.getValueAt(i, 0); // 鑾峰彇澶嶉€夋鐘舵€�
                String quantity = (String) moreInView.inventoryTable.getValueAt(i, 8); // 鑾峰彇杈撳叆鐨勬暟閲�
                String id = (String) moreInView.inventoryTable.getValueAt(i, 1);
                String inventory = (String) moreInView.inventoryTable.getValueAt(i, 5);
                if (isSelected != null && isSelected) { // 濡傛灉澶嶉€夋琚€変腑
                    // 渚嬪锛氭洿鏂板簱瀛�
                    RevisionItemService revisionItemService = new RevisionItemServiceImpl();
                    ck.setId(id);
                    String newInventory = String.valueOf(Integer.parseInt(inventory) + Integer.parseInt(quantity));
                    ck.setInventory(newInventory);
                    System.out.println("ck: " + ck.getId() + ", " + ck.getInventory());
                    revisionItemService.revisionMoreNumber1(ck);

                    // 璁板綍鍏ュ簱淇℃伅
                    bo.setDanhao("IN_MoreBound" + generateRandomNumber());
                    bo.setId((String) moreInView.inventoryTable.getValueAt(i, 1)); // 璐х墿缂栧彿
                    bo.setNumber(quantity); // 璁剧疆鍏ュ簱鏁伴噺
                    bo.setBoundtype("鍏ュ簱");
                    bo.setName(loginView.getUserTxt().getText());
                    bo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    addboundService.addBound(bo);
                }
            }

            JOptionPane.showMessageDialog(null, "鍏ュ簱鎴愬姛锛�", "鍏ュ簱", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private String generateRandomNumber() {
        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);
        return num1 + String.valueOf(num2) + num3 + num4;
    }
}

