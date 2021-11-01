package com.example.testjasypt.jasypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Security;

@Component
public class En {

    @Value("${vsl.name}")
    private String user;


    @PostConstruct
    public void test(){
        Security.addProvider(new BouncyCastleProvider());
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
        encryptor.setPassword("jj");

        String encrypt = encryptor.encrypt("123");

        String decrypt = encryptor.decrypt(encrypt);

        System.out.println(decrypt);
        System.out.println(user);


    }
}
