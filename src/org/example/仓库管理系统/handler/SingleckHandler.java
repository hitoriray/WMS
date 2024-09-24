package org.example.仓库管理系统.handler;

import org.example.仓库管理系统.dao.ckDao;
import org.example.仓库管理系统.service.impl.ShowDataInformation;
import org.example.仓库管理系统.view.Inquire.ShowckView;
import org.example.仓库管理系统.view.Inquire.SingleckView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SingleckHandler implements ActionListener {
    private SingleckView singleckView;
    public SingleckHandler(SingleckView singleckView){
        this.singleckView=singleckView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = singleckView.getname();

        if(name.equals(""))
        {
            JOptionPane.showMessageDialog(null,"请输入货物名称!!!!","查询",2);
            return;
        }
        List<ckDao> list = ShowDataInformation.getckSingle(name);
        if(list.size()==0){
            JOptionPane.showMessageDialog(null,"抱歉，暂无该产品","查询",2);
        }else {
            new ShowckView(list);
        }
    }
}
