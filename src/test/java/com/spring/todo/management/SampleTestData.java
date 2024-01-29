package com.spring.todo.management;

import com.spring.todo.management.model.EntityToDo;
import com.spring.todo.management.model.ToDoModel;

import java.time.LocalDateTime;
import java.util.List;

public class SampleTestData {

    public static ToDoModel sampleModelData() {
        return new ToDoModel(12L, "Adding a Todo", "Done",
                LocalDateTime.now(), LocalDateTime.of(2024, 01, 28, 9, 30),
                LocalDateTime.of(2024, 01, 31, 9, 30));
    }


    public static EntityToDo sampleEntityData() {
        return new EntityToDo(12L, "Adding a Todo", "Done",
                LocalDateTime.now(), LocalDateTime.of(2024, 01, 28, 9, 30),
                LocalDateTime.of(2024, 01, 31, 9, 30));
    }

    public static List<EntityToDo> sampleListEntityData() {
        return List.of(sampleEntityData());
    }
}
