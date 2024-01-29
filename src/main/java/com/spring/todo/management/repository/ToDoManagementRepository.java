package com.spring.todo.management.repository;

import com.spring.todo.management.model.EntityToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoManagementRepository extends JpaRepository<EntityToDo, Long> {


    List<EntityToDo> findAllByStatus(String status);
}
