package org.wenhu.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/26
 */
public class AliyunOss {

    public static String ossFileUpload(String fileName, InputStream fileInputStream) {
        //endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
        //如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
        //链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
        //endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
        //比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
        String endpoint = "https://oss-cn-zhangjiakou.aliyuncs.com";
        // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
        // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
        // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
        String accessKeyId = "LTAI5tQBbZY6bN6ck1DVQ5CT";
        String accessKeySecret = "DO9wB53zwaLN6XO0XLQiIQfq2aPcla";
        // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
        // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
        String bucketName = "wenhu";
        // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, fileInputStream);

        // 设置URL过期时间为10年  3600L* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        return url.toString();
    }

}
