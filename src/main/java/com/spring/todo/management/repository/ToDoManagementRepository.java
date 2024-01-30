package com.spring.todo.management.repository;

import com.spring.todo.management.model.EntityToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ToDoManagementRepository extends JpaRepository<EntityToDo, Long> {


    List<EntityToDo> findAllByStatus(String status);

    @Query(value = "SELECT * FROM TO_DO_LIST where DUE_DATE <= ?1 and status = ?2",nativeQuery = true)
    List<EntityToDo> findAllByDueDateAndStatus(LocalDateTime dateTime, String status);
}
