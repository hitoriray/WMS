package org.example.仓库管理系统.handler;
import org.example.仓库管理系统.dao.PermissionDao;
import org.example.仓库管理系统.service.PermissionService;
import org.example.仓库管理系统.service.impl.PermissionServiceImpl;
import org.example.仓库管理系统.view.*;
import org.example.仓库管理系统.view.File.FileView;
import org.example.仓库管理系统.view.Inquire.InquireView;
import org.example.仓库管理系统.view.Manager.ManagerView;
import org.example.仓库管理系统.view.Person.PersonView;
import org.example.仓库管理系统.view.in.InView;
import org.example.仓库管理系统.view.out.OutView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MainHandler extends KeyAdapter implements ActionListener {
    private  MainView mainView;
    private  LoginView loginView;
    public MainHandler(MainView mainView, LoginView loginView) {
        this.mainView=mainView;
        this.loginView=loginView;
    }


    PermissionDao permissionDao=new PermissionDao();
    int i ;
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton) e.getSource();
        String text = jButton.getText();
        PermissionService permissionService= new PermissionServiceImpl(mainView,loginView);
        if(text.equals("退出")){
            int i = JOptionPane.showConfirmDialog(null, "是否退出", "退出", JOptionPane.OK_CANCEL_OPTION);
            if(JOptionPane.OK_CANCEL_OPTION==i){
                return ;
            }else{
                System.exit(0);
            }
        }else if(text.equals("注销")){
            int i = JOptionPane.showConfirmDialog(null, "是否注销", "注销", JOptionPane.OK_CANCEL_OPTION);
            if(JOptionPane.OK_CANCEL_OPTION==i){
                return ;
            }{
                new LoginView();
                mainView.dispose();
            }
        }else  if(text.equals("查询")){


            i = permissionService.yanzhengInquire(permissionDao);
            if(i==1) {
                new InquireView();

            }else{
                JOptionPane.showMessageDialog(null,"抱歉你暂无此权限","查询",2);

            }
        }else if(text.equals("入库")) {

            i = permissionService.yanzhengInbound(permissionDao);
            if (i == 1) {
                new InView(loginView);

            } else {
                JOptionPane.showMessageDialog(null, "抱歉你暂无此权限", "入库", 2);

            }
        }
            else if(text.equals("出库")){
                i=permissionService.yanzhengOutbound(permissionDao);
                if(i==1){
                    new OutView(loginView);
                }
                else{
                    JOptionPane.showMessageDialog(null,"抱歉你暂无此权限","出库",2);
                }
            }
        else if(text.equals("仓库管理")){
            i=permissionService.yanzhengManager(permissionDao);
            if(i==1){
                new ManagerView();
            }
            else{
                JOptionPane.showMessageDialog(null,"抱歉你暂无此权限","仓库管理",2);
            }
        }
        else if(text.equals("人员档案管理"))
        {
            i=permissionService.yanzhengFile(permissionDao);
            if(i==1){
                new FileView(loginView);
            }
            else{
                JOptionPane.showMessageDialog(null,"抱歉你暂无此权限","人员档案管理",2);
            }
        }
        else if(text.equals("个人中心"))
        {
                new PersonView(loginView);
        }

    }
}
