package org.example.warehouse.view.Inquire;

import org.example.warehouse.dao.boundDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class INBoundView extends JFrame {
    JFrame jFrame=new JFrame("查询");
    public INBoundView(List<boundDao> list){
        super("查询");

        JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));

        String[] index={"单号","物品编号","入库数量","类型","操作人员名称","入库时间"};
        Object [][] data=new Object[list.size()][index.length];
        System.out.println("size:"+list.size());
        for(int i=0;i<list.size();i++){
            boundDao bo=list.get(i);
            data[i][0]=bo.getDanhao();
            data[i][1]=bo.getId();
            data[i][2]=bo.getNumber();
            data[i][3]=bo.getBoundtype();
            data[i][4]=bo.getName();
            data[i][5]=bo.getTime();
        }
        JTable table=new JTable(data,index);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setEnabled(false);
        table.getTableHeader().setResizingAllowed(false);
        jFrame.getContentPane().add(jPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        jFrame.setSize(800,600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);


    }
}
