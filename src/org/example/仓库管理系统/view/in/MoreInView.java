package org.example.仓库管理系统.view.in;

import org.example.仓库管理系统.handler.inHandler.MoreInHandler;
import org.example.仓库管理系统.view.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoreInView extends JFrame {
    JFrame jFrame =new JFrame();
    public DefaultTableModel tableModel;
    JLabel label =new JLabel("请输入货物信息:    ");
    JButton sureButton =new JButton("确认添加");
    JButton addButton = new JButton("添加行");
    JButton deleteButton = new JButton("删除行");
    JButton button =new JButton("查询货物信息");
    JButton ReportButton=new JButton("报表打印");
    public JTable table;
    MoreInHandler moreInHandler;

    public MoreInView(LoginView loginView){
        super("多物料添加");
        moreInHandler=new MoreInHandler(this,loginView);
        JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        label.setFont(new Font("楷体",Font.PLAIN,25));
        button.setFont(new Font("楷体",Font.PLAIN,18));
        addButton.setFont(new Font("楷体",Font.PLAIN,18));
        sureButton.setFont(new Font("楷体",Font.PLAIN,18));
        deleteButton.setFont(new Font("楷体",Font.PLAIN,18));
        ReportButton.setFont(new Font("楷体",Font.PLAIN,18));
        jPanel.add(label);
        jPanel.add(addButton);
        jPanel.add(sureButton);
        jPanel.add(deleteButton);
        jPanel.add(button);
        jPanel.add(ReportButton);
        String[] index={"货物编号","货物类型","添加货物数量","进出方式","人员名称"};
        Object[][] date=new Object[1][5];
               date[0][3]="多物料入库";
           date[0][4]=loginView.getUserTxt().getText();
        tableModel = new DefaultTableModel(date,index);
        table = new JTable(tableModel);//创建指定表格模型的表格
        JScrollPane scrollPane = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.addMouseListener(new MouseAdapter(){
            //发生了单击事件
            public void mouseClicked(MouseEvent e){

            }
        });
        addButton.addActionListener(moreInHandler);
        sureButton.addActionListener(moreInHandler);
        deleteButton.addActionListener(moreInHandler);
        button.addActionListener(moreInHandler);
        ReportButton.addActionListener(moreInHandler);
        getContentPane().add(jPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(1000,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);



    }




}
