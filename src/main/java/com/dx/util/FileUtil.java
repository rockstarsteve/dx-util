package com.dx.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Description: com.dx.util
 *
 * @author yaoj
 * @version 1.0
 * @copyright Copyright (c) 文理电信
 * @since 2020/4/11
 */
@Slf4j
public class FileUtil {


    /**
     * 查找指定文件下的所有文件对象
     *
     * @param dir        路径
     * @param suffixName 文件后缀数组
     * @return 文件列表
     */
    public static List<File> findAllFileInDir(String dir, String[] suffixName) {

        File file = new File(String.valueOf(dir));
        Collection<File> files = FileUtils.listFiles(file, suffixName, true);

        return (List<File>) files;
    }

    /**
     * 查找找文件夹下指定文件后缀名称的文件内容中带指定字符的文件
     *
     * @param word           单词
     * @param dir            路径
     * @param suffixFileName 后缀名称
     * @return 文件列表
     */
    public static List<File> findFilePathInDir(String word, String dir, String[] suffixFileName) {


        List<File> resultFileList = new ArrayList<>();

        //1:获取所有的目录下的所有指定名称的文件
        List<File> fileList = findAllFileInDir(dir, suffixFileName);

        //2:判断文件中是否存在指定字符串
        for (File file : fileList) {
            try {

                String fileContent = IOUtils.toString(new FileInputStream(file), "utf-8");
                if (fileContent.indexOf(word) > 0) {
//                    log.info("这个{}文件存在图片为{}的文件引用", file, word);
                    resultFileList.add(file);
                }
            } catch (Exception e) {
                log.info("错误读取数据");
                e.printStackTrace();
            }
        }
        return resultFileList;
    }

    /**
     * 获取相对路径位置
     *
     * @param file  文件
     * @param index 含几个"${splitString}",如0个是a.text,1是path{splitString}a.text
     * @return
     */
    public static String getFileAbsolutPath(File file, int index, String splitString) {

        String absolutPath = "";
        String absolutePath = file.getAbsolutePath();

        //报这个错的原因是因为在java中“/”是一个转义字符，所以需要用两个代表一个。(File.separator无法使用)
        String[] split = absolutePath.split("\\\\");

        //获取指定的数据
        List<String> stringList = Arrays.asList(split);
        List<String> stringList1 = stringList.subList(stringList.size() - index - 1, stringList.size());
        for (String s : stringList1) {
            absolutPath+=s+splitString;
        }
        absolutPath = absolutPath.substring(0, absolutPath.length() -1);

        return absolutPath;
    }

    public static void main(String[] args) {
        File file = new File("E:\\dev\\GRGHub-tianHeEast-vue\\src\\view\\customerService\\component\\right\\videoCall\\VideoCall.vue");

        String fileAbsolutPath = getFileAbsolutPath(file, 0, "/");

        System.out.println(fileAbsolutPath);

    }


}
