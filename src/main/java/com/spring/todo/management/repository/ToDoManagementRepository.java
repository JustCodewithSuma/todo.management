package com.spring.todo.management.repository;

import com.spring.todo.management.model.EntityToDo;
import com.spring.todo.management.model.ToDoModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoManagementRepository extends JpaRepository<EntityToDo,String> {

}
