package com.spring.todo.management.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@RequiredArgsConstructor
@Getter
@Setter
public class ToDoModel {

    public String description = "TestDescription";
    public enum Status{
        NOTDONE,
        DONE,
        PASTDUE;
    }
    public LocalDateTime creationDate = LocalDateTime.now();
    public LocalDateTime dueDate = LocalDateTime.of(2024,01,28,9,30);
    public LocalDateTime completedDate = LocalDateTime.of(2024,01,27,6,30);;
}
