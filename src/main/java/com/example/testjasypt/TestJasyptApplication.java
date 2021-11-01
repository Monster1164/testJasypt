package com.example.testjasypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.security.Security;

@SpringBootApplication
public class TestJasyptApplication {

    @Autowired
    private Environment env;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(){
        Security.addProvider(new BouncyCastleProvider());
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();


        String pwd = env.getProperty("jasypt.encryptor.password");
        String algorithm = env.getProperty("jasypt.encryptor.algorithm");

        config.setPassword(pwd);
        config.setAlgorithm(algorithm);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        // config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestJasyptApplication.class, args);
    }

}
