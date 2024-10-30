//package org.example.warehouse.handler.ckHandler;
//
//import org.example.warehouse.dao.warehouseDao;
//import org.example.warehouse.service.RevisionItemService;
//import org.example.warehouse.service.impl.RevisionItemServiceImpl;
//import org.example.warehouse.view.Manager.RevisionItemView;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class RevisionItemHandler implements ActionListener {
//    private RevisionItemView revisionItemView;
//
//    public RevisionItemHandler(RevisionItemView revisionItemView) {
//        this.revisionItemView = revisionItemView;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        RevisionItemService revisionItemService = new RevisionItemServiceImpl();
//        warehouseDao warehouseDao = revisionItemView.getItem();
//        if (revisionItemView.getItem().getId().equals("") || revisionItemView.getItem().getName().equals("") || revisionItemView.getItem().getType().equals("") || revisionItemView.getItem().getUnit().equals("") || revisionItemView.getItem().getRemark().equals("")) {
//            JOptionPane.showMessageDialog(null, "请输入完整信息！", "修改", 2);
//            return;
//        }
//        boolean ok = revisionItemService.revisionItem(warehouseDao);
//        if (ok) {
//            JOptionPane.showMessageDialog(null, "修改成功", "修改", 1);
//        } else {
//            JOptionPane.showMessageDialog(null, "修改失败", "修改", 2);
//        }
//
//    }
//}
