package com.example.demo.springawsdemo2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {
    private Credentials credentials = new Credentials();
    private String region;
    private S3 s3 = new S3();

    @Setter
    @Getter
    public static class Credentials {
        // getter/setter
        private String accessKey;  // env에서 주입
        private String secretKey;  // env에서 주입

    }
    @Getter
    public static class S3 {
        private String bucket;

        public void setBucket(String bucket) { this.bucket = bucket; }
    }

}
