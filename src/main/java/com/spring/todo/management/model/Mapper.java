package com.spring.todo.management.model;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static ToDoModel mapEntityToModel(EntityToDo entityToDo) {
        return new ToDoModel(entityToDo.getId(), entityToDo.description, entityToDo.status, entityToDo.creationDate, entityToDo.completedDate, entityToDo.dueDate);

    }

    public static EntityToDo mapModelToEntity(ToDoModel toDoModel) {
        return new EntityToDo(toDoModel.getId(), toDoModel.description, toDoModel.status, toDoModel.creationDate, toDoModel.completedDate, toDoModel.dueDate);

    }

    public static List<ToDoModel> mapEntityToModelList(List<EntityToDo> entityToDos) {
        return entityToDos.stream().map(Mapper::mapEntityToModel).collect(Collectors.toList());
    }

}
