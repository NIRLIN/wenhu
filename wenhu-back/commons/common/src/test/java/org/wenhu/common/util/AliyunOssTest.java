package org.wenhu.common.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/21
 */
class AliyunOssTest {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\Alldata\\Desktop\\0210312162408.jpg");

        InputStream inputStream = new FileInputStream(file);
        String aaa = AliyunOss.ossFileUpload("aaa", inputStream);
        System.out.println(aaa);
    }
}
