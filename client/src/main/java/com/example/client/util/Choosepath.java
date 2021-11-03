package com.example.client.util;


import javax.swing.*;
import java.awt.*;

public class Choosepath {
    private  static Component Select;
    private static int s1 = JFileChooser.FILES_AND_DIRECTORIES;
    private static int s2 = JFileChooser.DIRECTORIES_ONLY;
    private static int s3 = JFileChooser.ALLBITS;

    public static String choosefile(int arg0) {
        if (arg0 == 1) {
            JFileChooser jFileChooser = new JFileChooser();
            //文件和目录都能选择
            jFileChooser.setFileSelectionMode(s1);
            //设置文件选择器的默认路径
            jFileChooser.setCurrentDirectory(new java.io.File("D:\\"));
            //设置窗口标题
            jFileChooser.setDialogTitle("请选择文件或文件夹：");
            jFileChooser.setFont(new Font("新宋体", 0, 14)); // NOI18N
            jFileChooser.showSaveDialog(Select);
            //输出绝对路径
            String getpath = jFileChooser.getSelectedFile().getAbsolutePath();
            System.out.println(getpath);
            return getpath;
        } else {
            JFileChooser jFileChooser = new JFileChooser();
            //只能选择文件夹
            jFileChooser.setFileSelectionMode(s2);
            //设置文件选择器的默认路径
            jFileChooser.setCurrentDirectory(new java.io.File("D:\\"));
            //设置窗口标题
            jFileChooser.setDialogTitle("选择保存目录：");
            jFileChooser.setFont(new Font("新宋体", 0, 14)); // NOI18N
            jFileChooser.showSaveDialog(Select);
            //输出绝对路径
            String getpath = null;
            try {
                getpath = jFileChooser.getSelectedFile().getAbsolutePath();
            } catch (NullPointerException e) {
//                e.printStackTrace();
                System.out.println("未选择文件！");
            }
            System.out.println(getpath);
            return getpath;
        }

    }
}
