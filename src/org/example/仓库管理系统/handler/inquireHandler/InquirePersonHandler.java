package org.example.仓库管理系统.handler.inquireHandler;

import org.example.仓库管理系统.dao.UserTotalDao;
import org.example.仓库管理系统.service.impl.ShowDataInformation;
import org.example.仓库管理系统.view.File.InquirePersonSingleView;
import org.example.仓库管理系统.view.File.InquirePersonView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InquirePersonHandler implements ActionListener{
    private InquirePersonView inquirePersonView;
    public InquirePersonHandler(InquirePersonView inquirePersonView){
        this.inquirePersonView=inquirePersonView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton) e.getSource();
        String text= jButton.getText();
        if(text.equals("查询")) {

            String s = inquirePersonView.getInTxt().getText();
            if(s.equals("")){
                JOptionPane.showMessageDialog(null,"请输入查询信息!","查询",2);
                return;
            }
            List<UserTotalDao> list = ShowDataInformation.getInformationsingle(s);
            if(list.size()==0){
                JOptionPane.showMessageDialog(null,"暂无此人！","查询",2);
            }else{

                new InquirePersonSingleView(list);

            }
        }else if(text.equals("删除")){

        }
    }
}
