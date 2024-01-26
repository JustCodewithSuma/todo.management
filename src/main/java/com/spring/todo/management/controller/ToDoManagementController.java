package com.spring.todo.management.controller;


import com.spring.todo.management.model.ToDoModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo/management/")
public class ToDoManagementController {

    @GetMapping("listTodos/{id}")
    public ToDoModel getMapping(@PathVariable("id")Integer id){
        return new ToDoModel();
    }

}
