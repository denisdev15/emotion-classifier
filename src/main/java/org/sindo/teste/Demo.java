package org.sindo.teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.sindo"})
public class Demo {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Demo.class, args);
    }
}

