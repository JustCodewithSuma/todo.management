package com.spring.todo.management.service;

import com.spring.todo.management.model.ToDoModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoManagementService {

    ResponseEntity<ToDoModel> retrieveItem(Long id);

    ResponseEntity<List<ToDoModel>> retrieveAllItem();

    ResponseEntity<List<ToDoModel>> retrieveAllItem(String status);

    ResponseEntity<ToDoModel> addItem(ToDoModel toDoModel);

    ResponseEntity<ToDoModel> updateItem(ToDoModel toDoModel);

    ResponseEntity<ToDoModel> updateDescriptionOfToDo(Long id, String description);

    ResponseEntity<ToDoModel> updateStatusOfToDo(Long id, String status);
}
