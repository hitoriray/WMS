package org.example.仓库管理系统.view.Manager;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.handler.ckHandler.SetupHandler;
import org.example.仓库管理系统.service.impl.getDBImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RevisionSetupView extends JFrame {
    JFrame jFrame=new JFrame("设置");
    JPanel jPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,30,25));
    JLabel label=new JLabel("货物：");
    JComboBox labelJB = new JComboBox();
    JLabel max=new JLabel("最大值：");
    JComboBox maxJB = new JComboBox();

    JLabel min=new JLabel("最小值：");
    JComboBox minJB = new JComboBox();
    JButton button=new JButton("确认");
    SetupHandler setupHandler;
    public RevisionSetupView(){
        super("修改设置");
        setupHandler= new SetupHandler(this);
        Container contentPane =getContentPane();

        getDBImpl getDB = new getDBImpl();
        List<ckDao> list = getDB.get();
        {
         for(int t=0;t< list.size();t++){
             ckDao ck = list.get(t);

             labelJB.addItem(ck.getName());
         }

        }
        JLabel max=new JLabel("最大值：");

        {
            for(int t=0;t< list.size();t++) {
                ckDao ck = list.get(t);
                maxJB.addItem(ck.getMax());
            }

        }
        JLabel min=new JLabel("最小值：");

        {
            for(int t=0;t< list.size();t++) {
                ckDao ck = list.get(t);

                minJB.addItem(ck.getMin());
            }

        }
        Font font = new Font("宋体", Font.PLAIN, 25);
        Dimension dimension = new Dimension(10, 30);
        label.setFont(font);
        max.setFont(font);
        min.setFont(font);
        button.setFont(font);
        jPanel.add(label);
        jPanel.add(labelJB);
        maxJB.setEditable(true);
        jPanel.add(max);
        jPanel.add(maxJB);
        minJB.setEditable(true);
        jPanel.add(min);
        jPanel.add(minJB);
        button.addActionListener(setupHandler);
        jPanel.add(button);

        contentPane.add(jPanel);
        setSize(300,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }
    public ckDao getSetup(){
        ckDao ck=new ckDao();
        String name=label.getText();
        ck.setName(labelJB.getSelectedItem().toString());
        ck.setMin(minJB.getSelectedItem().toString());
        ck.setMax(maxJB.getSelectedItem().toString());
        return ck;
    }

    public static void main(String[] args) {
        new RevisionSetupView();
    }


}
