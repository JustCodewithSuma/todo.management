package com.spring.todo.management.controller;


import com.spring.todo.management.model.ToDoModel;
import com.spring.todo.management.service.ToDoManagementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo/management/")
@Validated
public class ToDoManagementController {

    @Autowired
    ToDoManagementService toDoManagementService;

    @GetMapping("listTodo/{id}")
    public ResponseEntity<ToDoModel> getItem(@NotNull @PathVariable("id") Integer id) {
        return toDoManagementService.retrieveItem(id.longValue());
    }

    @GetMapping("listTodo")
    public ResponseEntity<List<ToDoModel>> getAllItem(@RequestParam Optional<String> status) {
        if (status.isPresent())
            return toDoManagementService.retrieveAllItem(status.get());
        else
            return toDoManagementService.retrieveAllItem();
    }

    @PutMapping("listTodo/update/{id}")
    public ResponseEntity<ToDoModel> updateItem(@NotNull @PathVariable("id") Integer id, @RequestParam Optional<String> description, @RequestParam Optional<String> status) {

        if (description.isPresent())
            return toDoManagementService.updateDescriptionOfToDo(id.longValue(), description.get());
        else if (status.isPresent())
            return toDoManagementService.updateStatusOfToDo(id.longValue(), status.get());
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @PostMapping("listTodo/add")
    public ResponseEntity<ToDoModel> addItem(@Valid @RequestBody ToDoModel todoModel) {
        return toDoManagementService.addItem(todoModel);
    }

}
