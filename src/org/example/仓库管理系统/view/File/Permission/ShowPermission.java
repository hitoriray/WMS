package org.example.仓库管理系统.view.File.Permission;

import org.example.仓库管理系统.dao.PermissionDao;
import org.example.仓库管理系统.handler.MouserHandler;
import org.example.仓库管理系统.handler.ReviosionPermissionHandler1;
import org.example.仓库管理系统.service.impl.ShowDataInformation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ShowPermission extends  JFrame {
    private ShowPermission showPermission;
    JTextField revision =new JTextField("单击可修改");
    JButton inquirebutton=new JButton("查询人员");
    JTextField textField=new JTextField();
    private DefaultTableModel model;
    ReviosionPermissionHandler1 reviosionPermissionHandler1;
    MouserHandler mouserHandler;
    private List<PermissionDao> list;
    public  ShowPermission(List<PermissionDao> list){
        super("人员权限");
        this.list=list;

        mouserHandler=new MouserHandler(this);
        reviosionPermissionHandler1 =new ReviosionPermissionHandler1(this);
        JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        revision.setFont(new Font("楷体",Font.PLAIN,18));
        inquirebutton.setFont(new Font("楷体",Font.PLAIN,18));
        textField.setPreferredSize(new Dimension(100,30));
        jPanel.add(inquirebutton);
        jPanel.add(textField);
        jPanel.add(revision);
        inquirebutton.addActionListener(reviosionPermissionHandler1);
        //table添加
        String[] index={"姓名","身份证号","查询权限","入库权限","出库权限","仓库管理权限","人员档案管理权限"};
        Object [][] data=new Object[list.size()][index.length+1];
        System.out.println("size:"+list.size());
        for(int i=0;i<list.size();i++){
            PermissionDao permissionDao =list.get(i);
            data[i][0]=permissionDao.getName();
            data[i][1]=permissionDao.getIDnumber();
            data[i][2]=permissionDao.getInquire();
            data[i][3]=permissionDao.getInbound();
            data[i][4]=permissionDao.getOutbound();
            data[i][5]=permissionDao.getManager();
            data[i][6]=permissionDao.getFile();
        }
        JTable table=new JTable(data,index);
        JScrollPane scrollPane = new JScrollPane(table);
        table.getTableHeader().setResizingAllowed(false);
        table.addMouseListener(mouserHandler);
       getContentPane().add(jPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(800,600);
        setResizable(false);
       setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


    }
    public String getInformation(){
        return textField.getText().toString();

    }
    public String getname(int row){
        return  list.get(row).getName();
    }
    public String getidnumber(int row){
        return  list.get(row).getIDnumber();
    }

}
