package com.dx.service;

import com.dx.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;

/**
 * Description: com.dx.main
 *
 * @author yaoj
 * @version 1.0
 * @copyright Copyright (c) 文理电信
 * @since 2020/4/11
 */
@Slf4j
public class ImageService {

    public static void main(String[] args) {

        //图片地址
        String imgDir = "E:\\dev\\GRGHub-tianHeEast-vue\\src\\assets\\images";
        //要查询图片的后缀
        String[] suffixName = {"jpg"};
        //查询文字的文件所在地址
        String fileHasWordDir = "E:\\dev\\GRGHub-tianHeEast-vue\\src\\view";
        //要查询文字的文件的后缀
        String[] suffixFileName = {"vue"};

        //1：查询到所有的图片文件
        List<File> fileList = FileUtil.findAllFileInDir(imgDir, suffixName);

        //2：查询文件在哪里出现了
        for (File file : fileList) {
            //获取文件一个分割符的相对路径
            String fileAbsolutPath = FileUtil.getFileAbsolutPath(file, 0, "/");

            List<File> filePathInDir = FileUtil.findFilePathInDir(fileAbsolutPath, fileHasWordDir, suffixFileName);

            if (CollectionUtils.isEmpty(filePathInDir)){
                log.info("这个{}文件没有被使用",file.getAbsolutePath());
            }else {
                log.info("现在{}文件已经用了",file.getAbsolutePath());
            }

        }

    }

}
