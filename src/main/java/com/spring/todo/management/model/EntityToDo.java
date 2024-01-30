package com.spring.todo.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "ToDoList")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntityToDo {
    @Id
    @GeneratedValue
    public Long id;
    @Column
    public String description;
    @Column
    public String status;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime creationDate;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime dueDate;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime completedDate;

}
