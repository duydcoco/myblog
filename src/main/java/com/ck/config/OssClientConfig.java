package com.ck.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssClientConfig {
    @Value("${oss.endpoint}")
    private String ossEndPoint;
    @Value("${oss.accessKeyId}")
    private String ossAccessKeyId;
    @Value("${oss.accessKeySecret}")
    private String ossAccesssSecret;
    @Value("${oss.bucketName}")
    private String ossBucketName;

    @Bean
    public OSSClient getOSSClient(){
        return new OSSClient(this.ossEndPoint,this.ossAccessKeyId,this.ossAccesssSecret);
    }

    public String getOssEndPoint() {
        return ossEndPoint;
    }

    public String getOssAccessKeyId() {
        return ossAccessKeyId;
    }

    public String getOssAccesssSecret() {
        return ossAccesssSecret;
    }

    public String getOssBucketName() {
        return ossBucketName;
    }
}
