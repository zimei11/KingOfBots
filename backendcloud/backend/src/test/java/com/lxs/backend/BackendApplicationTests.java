package com.lxs.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.encode("111111"));
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.encode("123456"));

    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class,args);

    }

}
