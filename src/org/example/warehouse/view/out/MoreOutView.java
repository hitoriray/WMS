package org.example.warehouse.view.out;

import org.example.warehouse.handler.outHander.MoreOutHandler;
import org.example.warehouse.view.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoreOutView extends JFrame {
    JFrame jFrame =new JFrame();
    public DefaultTableModel tableModel;
    JLabel label =new JLabel("请输入货物信息:    ");
    JButton sureButton =new JButton("确认出库");
    JButton addButton = new JButton("添加行");
    JButton deleteButton = new JButton("删除行");
    JLabel DHlabel=new JLabel("请输入出仓单号：");
    JButton Button=new JButton("查询货物信息");
    JButton ReportButton=new JButton("报表打印");

    JTextField DHField=new JTextField();
    public JTable table;
    MoreOutHandler moreOutHandler;

    public MoreOutView(LoginView loginView){
        super("多物料出库");
        moreOutHandler=new MoreOutHandler(this,loginView);
        JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        label.setFont(new Font("楷体",Font.PLAIN,25));
        Button.setFont(new Font("楷体",Font.PLAIN,18));
        addButton.setFont(new Font("楷体",Font.PLAIN,18));
        sureButton.setFont(new Font("楷体",Font.PLAIN,18));
        deleteButton.setFont(new Font("楷体",Font.PLAIN,18));
        DHlabel.setFont(new Font("楷体",Font.PLAIN,18));
        ReportButton.setFont(new Font("楷体",Font.PLAIN,18));
        DHField.setPreferredSize(new Dimension(100, 30));
        jPanel.add(label);
        jPanel.add(addButton);
        jPanel.add(sureButton);
        jPanel.add(deleteButton);
        jPanel.add(Button);
        jPanel.add(ReportButton);
        String[] index={"货物编号","货物类型","出库数量","进出方式","人员名称"};
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
        addButton.addActionListener(moreOutHandler);
        sureButton.addActionListener(moreOutHandler);
        deleteButton.addActionListener(moreOutHandler);
        Button.addActionListener(moreOutHandler);
        ReportButton.addActionListener(moreOutHandler);
        getContentPane().add(jPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(900,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }



}
