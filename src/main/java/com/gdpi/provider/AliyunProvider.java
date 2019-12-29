package com.gdpi.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class AliyunProvider {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    @Value("${aliyun.endpoint}")
    String endpoint;
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    @Value("${aliyun.accessKeyId}")
    String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    String accessKeySecret;
    @Value("${aliyun.bucketName}")
    String bucketName;

    public String upload(InputStream fileStream,String fileName) {
        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
            return null;
            //throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = generatedFileName;
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        //new ByteArrayInputStream(content.getBytes())
        String content = fileName;
        ossClient.putObject(bucketName, objectName,fileStream);



        // 关闭OSSClient。
        ossClient.shutdown();

        return generatedFileName;
    }
}
