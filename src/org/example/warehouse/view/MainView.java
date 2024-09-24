package org.example.warehouse.view;

import org.example.warehouse.handler.MainHandler;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    JFrame jFrame = new JFrame();
    SpringLayout springLayout = new SpringLayout();
    JPanel CenterPanel = new JPanel(springLayout);
    JLabel NamePanel = new JLabel("仓库管理系统中心界面", JLabel.CENTER);
    JButton InquireBtn = new JButton("查询");
    JButton InBtn = new JButton("入库");
    JButton OutBtn = new JButton("出库");
    JButton ManagerBtn = new JButton("仓库管理");
    JButton FileBtn = new JButton("人员档案管理");
    JButton PersonBtn = new JButton("个人中心");
    JButton LogoutBtn = new JButton("注销");
    JButton ExitBtn = new JButton("退出");
    MainHandler mainHandler;

    public MainView(LoginView loginView) {
        super("仓库管理系统");

        mainHandler = new MainHandler(this, loginView);
        Container contentPane = getContentPane();
        NamePanel.setFont(new Font("楷体", Font.PLAIN, 100));
        InquireBtn.setFont(new Font("华文行楷", Font.PLAIN, 30));
        InBtn.setFont(new Font("华文行楷", Font.PLAIN, 30));
        OutBtn.setFont(new Font("华文行楷", Font.PLAIN, 30));
        ManagerBtn.setFont(new Font("华文行楷", Font.PLAIN, 30));
        FileBtn.setFont(new Font("华文行楷", Font.PLAIN, 30));
        PersonBtn.setFont(new Font("华文行楷", Font.PLAIN, 30));
        LogoutBtn.setFont(new Font("华文行楷", Font.PLAIN, 30));
        ExitBtn.setFont(new Font("华文行楷", Font.PLAIN, 30));


        //spring 布局
        springLayout.putConstraint(SpringLayout.WEST, InBtn, 30, SpringLayout.EAST, InquireBtn);
        springLayout.putConstraint(SpringLayout.NORTH, InBtn, 0, SpringLayout.NORTH, InquireBtn);

        springLayout.putConstraint(SpringLayout.WEST, OutBtn, 30, SpringLayout.EAST, InBtn);
        springLayout.putConstraint(SpringLayout.NORTH, OutBtn, 0, SpringLayout.NORTH, InquireBtn);

        springLayout.putConstraint(SpringLayout.WEST, ManagerBtn, 30, SpringLayout.EAST, OutBtn);
        springLayout.putConstraint(SpringLayout.NORTH, ManagerBtn, 0, SpringLayout.NORTH, InquireBtn);

        springLayout.putConstraint(SpringLayout.WEST, FileBtn, 30, SpringLayout.EAST, ManagerBtn);
        springLayout.putConstraint(SpringLayout.NORTH, FileBtn, 0, SpringLayout.NORTH, InquireBtn);

        springLayout.putConstraint(SpringLayout.WEST, PersonBtn, 30, SpringLayout.EAST, FileBtn);
        springLayout.putConstraint(SpringLayout.NORTH, FileBtn, 0, SpringLayout.NORTH, InquireBtn);

        springLayout.putConstraint(SpringLayout.WEST, PersonBtn, 30, SpringLayout.EAST, FileBtn);
        springLayout.putConstraint(SpringLayout.NORTH, PersonBtn, 0, SpringLayout.NORTH, InquireBtn);

        springLayout.putConstraint(SpringLayout.WEST, LogoutBtn, 1200, SpringLayout.EAST, InquireBtn);
        springLayout.putConstraint(SpringLayout.NORTH, LogoutBtn, 570, SpringLayout.SOUTH, InquireBtn);

        springLayout.putConstraint(SpringLayout.WEST, ExitBtn, 10, SpringLayout.EAST, LogoutBtn);
        springLayout.putConstraint(SpringLayout.NORTH, ExitBtn, 0, SpringLayout.NORTH, LogoutBtn);


        CenterPanel.add(InquireBtn);
        CenterPanel.add(InBtn);
        CenterPanel.add(OutBtn);
        CenterPanel.add(ManagerBtn);
        CenterPanel.add(FileBtn);
        CenterPanel.add(PersonBtn);
        CenterPanel.add(LogoutBtn);
        CenterPanel.add(ExitBtn);
        InquireBtn.addActionListener(mainHandler);
        InBtn.addActionListener(mainHandler);
        OutBtn.addActionListener(mainHandler);
        ManagerBtn.addActionListener(mainHandler);
        FileBtn.addActionListener(mainHandler);
        PersonBtn.addActionListener(mainHandler);
        LogoutBtn.addActionListener(mainHandler);
        ExitBtn.addActionListener(mainHandler);
        JLabel imgLabel = new JLabel();
        ImageIcon img = new ImageIcon("src/org/example/warehouse/background.jpg");
        img.setImage(img.getImage().getScaledInstance(2000, 800, Image.SCALE_DEFAULT));
        imgLabel.setIcon(img);
        CenterPanel.add(imgLabel);

        contentPane.add(NamePanel, BorderLayout.NORTH);
        contentPane.add(CenterPanel, BorderLayout.CENTER);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
