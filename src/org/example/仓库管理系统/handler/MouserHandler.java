package org.example.仓库管理系统.handler;

import org.example.仓库管理系统.view.File.Permission.RevisionPermissionView;
import org.example.仓库管理系统.view.File.Permission.ShowPermission;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouserHandler implements MouseListener {
    private ShowPermission showPermission;
    public MouserHandler(ShowPermission showPermission){
        this.showPermission=showPermission;
    }    @Override
    public void mouseClicked(MouseEvent e) {
        int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置
                int col=((JTable)e.getSource()).columnAtPoint(e.getPoint());
                String banner = "鼠标当前点击位置的坐标是" + row + "," + col;
                System.out.println(banner);
                new RevisionPermissionView(showPermission.getname(row),showPermission.getidnumber(row),showPermission);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
