package org.example.仓库管理系统.handler.inHandler;

import org.example.仓库管理系统.dao.boundDao;
import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.service.AddboundService;
import org.example.仓库管理系统.service.RevisionckService;
import org.example.仓库管理系统.service.UserService;
import org.example.仓库管理系统.service.impl.AddboundServiceImpl;
import org.example.仓库管理系统.service.impl.RevisionckServiceImpl;
import org.example.仓库管理系统.service.impl.ShowDataInformation;
import org.example.仓库管理系统.service.impl.UserServiceImpl;
import org.example.仓库管理系统.view.Inquire.ShowckView;
import org.example.仓库管理系统.view.LoginView;
import org.example.仓库管理系统.view.Report.ReportView;
import org.example.仓库管理系统.view.in.MoreInView;

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
        if (text.equals("添加行")) {
            String[] rowValues = {"", "", "", "多物料入库", loginView.getUserTxt().getText()};//创建表格数组
            moreInView.tableModel.addRow(rowValues);
        } else if (text.equals("确认添加")) {
            int rows=moreInView.table.getRowCount();
            int  cols=moreInView.table.getColumnCount();
            System.out.println("rows"+rows);
            System.out.println("cols"+cols);
            UserService userService=new UserServiceImpl();
            ckDao ck=new ckDao();
            boundDao bo=new boundDao();
            AddboundService addboundService =new AddboundServiceImpl();
            for(int  i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(moreInView.table.getValueAt(i,j).equals("")||moreInView.table.getValueAt(i,j)==null)
                    {
                        JOptionPane.showMessageDialog(null, "您填入的信息不完整！", "入库", 2);
                        return;
                    }
                }
            }
            for(int  i=0;i<rows;i++){
                boolean yanzhengid = userService.yanzhengid((String) moreInView.table.getValueAt(i, 0),(String) moreInView.table.getValueAt(i, 1));
                if(!yanzhengid){
                    JOptionPane.showMessageDialog(null,"您输入的信息有误","入库",2);
                    return;
                }
            }
            int num1 = (int) (Math.random()*10);
            int num2 = (int) (Math.random()*10);
            int num3 = (int) (Math.random()*10);
            int num4 = (int) (Math.random()*10);
            String num= num1 +String.valueOf(num2)+ num3 + num4;
            for(int i=0;i<rows;i++){
                ck.setId((String) moreInView.table.getValueAt(i,0));
                ck.setInventory((String) moreInView.table.getValueAt(i,2));
                RevisionckService revisionckService=new RevisionckServiceImpl();
                revisionckService.revisionMoreNumber(ck);
                bo.setDanhao("IN_MoreBound"+num);
                bo.setId((String) moreInView.table.getValueAt(i,0));
                bo.setNumber((String) moreInView.table.getValueAt(i,2));
                bo.setBoundtype("入库");
                bo.setName(loginView.getUserTxt().getText());
                Date date=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                bo.setTime(formatter.format(date));
                addboundService.addbound(bo);
            }
            JOptionPane.showMessageDialog(null,"入库成功!"+"您的入库编号为"+"IN_MoreBound"+num,"入库",1);

        } else if (text.equals("删除行")) {
            int selectedRow = moreInView.table.getSelectedRow();
            System.out.println(selectedRow + ";;;;;;;;;;;;;;;");
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(null,"请选择要删除的行","删除",2);
                    return;
                }
                moreInView.tableModel.removeRow(selectedRow);
            }
        else if(text.equals("查询货物信息")){
            List<ckDao> list = ShowDataInformation.getck();
            new ShowckView(list);
        }else if(text.equals("报表打印")){
            new ReportView();
        }
        }
    }

