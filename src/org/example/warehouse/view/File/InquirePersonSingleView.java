package org.example.warehouse.view.File;

import org.example.warehouse.dao.UserTotalDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InquirePersonSingleView extends  JFrame{


    public InquirePersonSingleView(List<UserTotalDao> list) {
        super("人员信息");

        //table添加
        String[] index={"姓名","身份证","出生日期","性别","籍贯","家庭住址","类型","联系电话"};
        Object [][] data=new Object[list.size()][index.length];
        System.out.println("size:"+list.size());
        for(int i=0;i<list.size();i++){
            UserTotalDao userTotalDao =list.get(i);
            data[i][0]=userTotalDao.getName();
            data[i][1]=userTotalDao.getIDnumber();
            data[i][2]=userTotalDao.getDate();
            data[i][3]=userTotalDao.getGender();
            data[i][4]=userTotalDao.getOrigin();
            data[i][5]=userTotalDao.getAddress();
            data[i][6]=userTotalDao.getType();
            data[i][7]=userTotalDao.getPhone();
        }
        JTable table=new JTable(data,index);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setEnabled(false);
        table.getTableHeader().setResizingAllowed(false);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}
