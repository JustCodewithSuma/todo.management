package com.spring.todo.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToDoManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoManagementApplication.class, args);
    }

}
