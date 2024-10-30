package org.example.warehouse.view;

import org.example.warehouse.service.impl.Database;
import org.example.warehouse.utils.JDBCUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VersionInfoView extends JFrame {
    private JLabel versionLabel; // 用于显示版本号
    private static final Database database = new Database(); // 数据库操作对象

    public VersionInfoView() {
        setTitle("版本信息");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建主面板并设置背景色
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 设置间距

        // 创建标题标签
        JLabel titleLabel = new JLabel("版本信息");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 150));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);


        String currentVersion = database.getCurrentVersion(); // 从数据库中获取当前版本

        // 添加版本信息
        String[] versionInfo = {
                "软件名称: 仓库管理系统",
                "版本号: "+ currentVersion , // 初始版本号
                "作者: Your Name",
                "发布日期: 2024年10月22日",
                "联系方式: example@example.com"
        };

        gbc.gridwidth = 1; // 重置gridwidth
        for (int i = 0; i < versionInfo.length; i++) {
            JLabel infoLabel = new JLabel(versionInfo[i]);
            infoLabel.setFont(new Font("宋体", Font.PLAIN, 16));
            gbc.gridx = 0;
            gbc.gridy = i + 1; // 从第一行开始
            mainPanel.add(infoLabel, gbc);
            // 如果是版本号，保存到versionLabel
            if (i == 1) {
                versionLabel = infoLabel; // 版本号标签
            }
        }

        // 添加检查更新按钮
        JButton checkUpdateButton = new JButton("检查更新");
        checkUpdateButton.setFont(new Font("宋体", Font.BOLD, 16));
        checkUpdateButton.setBackground(new Color(50, 150, 50));
        checkUpdateButton.setForeground(Color.WHITE);
        checkUpdateButton.setFocusPainted(false);
        checkUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AutoUpdater.isUpdateAvailable()) {
                    int response = JOptionPane.showConfirmDialog(
                            VersionInfoView.this,
                            "检测到新版本，是否要进行更新？",
                            "检查更新",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        if (AutoUpdater.downloadAndExtractNewVersion()) {
                            String newVersion = AutoUpdater.getLatestVersion();
                            updateVersionInfo(newVersion); // 更新版本号
                            JOptionPane.showMessageDialog(VersionInfoView.this, "更新完成！即将重新启动应用。", "更新成功", JOptionPane.INFORMATION_MESSAGE);
                            AutoUpdater.restartApplication(); // 重新启动应用
                        } else {
                            JOptionPane.showMessageDialog(VersionInfoView.this, "更新失败，请稍后重试。", "更新失败", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(VersionInfoView.this, "您选择了不进行更新。", "更新取消", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(VersionInfoView.this, "没有可用的更新。", "无更新", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = versionInfo.length + 1; // 添加在最后
        mainPanel.add(checkUpdateButton, gbc);

        add(mainPanel);
        setVisible(true);
    }

    // 更新版本号的方法
    public void updateVersionInfo(String newVersion) {
        System.out.println("准备更新版本号到：" + newVersion);
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE version_table SET version = ?")) {
            statement.setString(1, newVersion);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("版本号更新成功。");
            } else {
                System.out.println("没有行受到影响，可能是更新失败。");
            }
        } catch (SQLException e) {
            System.err.println("更新数据库版本号时发生错误：" + e.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VersionInfoView());
    }
}
