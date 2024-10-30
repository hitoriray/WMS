package org.example.warehouse.view;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FolderDownloader {
    // 下载文件的URL
    private static final String FILE_URL = "http://localhost/warehouse.zip";
    // 保存下载的ZIP文件的路径
    private static final String SAVE_ZIP_PATH = "E:/code/warehouse.zip";
    // 解压后的文件夹路径
    private static final String EXTRACT_FOLDER_PATH = "E:/code/day1";

    public static void main(String[] args) {
        if (downloadFile(FILE_URL, SAVE_ZIP_PATH)) {
            System.out.println("文件下载成功：" + SAVE_ZIP_PATH);
            if (unzip(SAVE_ZIP_PATH, EXTRACT_FOLDER_PATH)) {
                System.out.println("文件解压成功到：" + EXTRACT_FOLDER_PATH);
            } else {
                System.out.println("文件解压失败。");
            }
        } else {
            System.out.println("文件下载失败。");
        }
    }

    /**
     * 下载文件并保存到指定路径
     */
    public static boolean downloadFile(String fileUrl, String savePath) {
        try {
            System.out.println("开始下载文件：" + fileUrl);
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

    /**
     * 解压ZIP文件到指定文件夹
     */
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
}
