/*
* @author PhatLT
* @date 2025-06-03
* @description Main Spring Boot Application class
*/
package vn.com.phat.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mirage.repository.config.EnableMirageRepositories;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}
