package com.spring.todo.management.model;

import com.spring.todo.management.validator.Create;
import com.spring.todo.management.validator.Update;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToDoModel {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    public Long id;
    public String description;
    @NotNull
    public String status;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime creationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime dueDate;
    @FutureOrPresent(message = "Completion date should be more than 5min from present or in future.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime completedDate;
}
