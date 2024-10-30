package org.example.warehouse.view;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.example.warehouse.service.impl.Database;

public class AutoUpdater {

    private static final String VERSION_URL = "http://localhost/version.txt"; // 存放服务器上版本信息的文件 URL
    private static final String FILE_URL = "http://localhost/warehouse.zip"; // 存放在服务器上的压缩包文件（ZIP文件）下载 UR
    private static final String SAVE_ZIP_PATH = "E:/code/warehouse.zip"; // 下载的ZIP文件保存到本地的路径
    private static final String EXTRACT_FOLDER_PATH = "E:/code/day1"; // 解压后的文件夹保存位置
    private static final Database database = new Database(); // 数据库操作对象

    public static void main(String[] args) {
        // 启动时检查更新
        if (isUpdateAvailable()) {
            System.out.println("检测到新版本，开始下载...");
            if (downloadAndExtractNewVersion()) {
                System.out.println("下载完成，更新数据库中的版本号...");
                updateVersionInDatabase(); // 更新数据库中的版本号
                System.out.println("更新完成，重新启动应用...");
                restartApplication();
            } else {
                System.err.println("下载失败，继续运行当前版本...");
                launchApplication();
            }
        } else {
            System.out.println("没有可用的更新，继续运行当前版本...");
            launchApplication();
        }
    }

    // 检查服务器上是否有新版本
    public static boolean isUpdateAvailable() {
        String currentVersion = database.getCurrentVersion(); // 从数据库中获取当前版本
        try {
            System.out.println("检查版本更新：" + VERSION_URL);
            URL url = new URL(VERSION_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String latestVersion = reader.readLine();
            reader.close();

            System.out.println("最新版本：" + latestVersion);
            System.out.println("当前版本：" + currentVersion);

            // 比较版本号
            return !currentVersion.equals(latestVersion);

        } catch (IOException e) {
            System.err.println("检查更新时发生错误：" + e.getMessage());
            return false;
        }
    }

    // 下载最新版本的ZIP文件并解压
    public static boolean downloadAndExtractNewVersion() {
        System.out.println("开始下载文件：" + FILE_URL);
        if (downloadFile(FILE_URL, SAVE_ZIP_PATH)) {
            System.out.println("文件下载成功：" + SAVE_ZIP_PATH);
            return unzip(SAVE_ZIP_PATH, EXTRACT_FOLDER_PATH);
        } else {
            System.out.println("文件下载失败。");
            return false;
        }
    }

    // 下载文件并保存到指定路径
    public static boolean downloadFile(String fileUrl, String savePath) {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                File saveDir = new File(savePath).getParentFile();
                if (saveDir != null && !saveDir.exists()) {
                    saveDir.mkdirs(); // 创建缺失的目录
                }

                try (InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("文件下载完成。");
                    return true;
                } catch (IOException e) {
                    System.err.println("文件写入错误：" + e.getMessage());
                    return false;
                }
            } else {
                System.err.println("服务器响应失败，响应代码：" + responseCode);
                return false;
            }

        } catch (IOException e) {
            System.err.println("下载时发生错误：" + e.getMessage());
            return false;
        }
    }

    // 解压ZIP文件到指定文件夹
    public static boolean unzip(String zipFilePath, String destDirectory) {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                File filePath = new File(destDirectory, entry.getName());
                if (entry.isDirectory()) {
                    filePath.mkdirs();
                } else {
                    // 确保父文件夹存在
                    filePath.getParentFile().mkdirs();
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipIn.read(buffer)) != -1) {
                            bos.write(buffer, 0, bytesRead);
                        }
                    }
                }
                zipIn.closeEntry();
            }
            System.out.println("解压完成。");
            return true;
        } catch (IOException e) {
            System.err.println("解压文件时发生错误：" + e.getMessage());
            return false;
        }
    }

    // 重新启动应用程序
    public static void restartApplication() {
        try {
            System.out.println("正在重新启动应用程序...");
            // 执行解压后的 JAR 文件
            Runtime.getRuntime().exec("java -jar " + EXTRACT_FOLDER_PATH + "/yourapp.jar");
            System.out.println("已启动新版本，退出当前程序...");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("重新启动时发生错误：" + e.getMessage());
        }
    }

    // 启动实际的应用程序逻辑
    private static void launchApplication() {
        // 启动你项目中的实际逻辑，例如显示Swing窗口
        System.out.println("启动应用程序...");
        // TODO: 在这里添加实际的Swing应用程序启动代码
    }

    // 更新数据库中的版本号
    private static void updateVersionInDatabase() {
        String latestVersion = getLatestVersion(); // 获取最新版本
        database.updateVersion(latestVersion); // 更新版本号
    }

    // 获取最新版本号
    public static String getLatestVersion() {
        try {
            URL url = new URL(VERSION_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String latestVersion = reader.readLine();
            reader.close();
            return latestVersion;
        } catch (IOException e) {
            System.err.println("获取最新版本号时发生错误：" + e.getMessage());
            return null;
        }
    }
}
