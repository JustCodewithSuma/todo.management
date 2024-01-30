package com.spring.todo.management.service;

import com.spring.todo.management.model.EntityToDo;
import com.spring.todo.management.repository.ToDoManagementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UpdateScheduler {


    @Autowired
    ToDoManagementRepository toDoManagementRepository;

    @Scheduled(fixedRateString = "${statusUpdateRule.scheduleTimer}", timeUnit = TimeUnit.HOURS, zone = "UTC")
    public void updatePastDueDate(){
        log.info("Scheduler running");
        LocalDateTime timeNow = LocalDateTime.now();
        List<EntityToDo> responseFromDb = toDoManagementRepository.findAllByDueDateAndStatus(timeNow,"not done");

        if(!responseFromDb.isEmpty()){
            List<EntityToDo> entityToUpdate = responseFromDb.stream().filter(entityToDo -> entityToDo.getDueDate().isBefore(timeNow)).toList();
            if(!entityToUpdate.isEmpty()) {
                entityToUpdate.forEach(entityToDo -> {
                    if (entityToDo.getId() != null) {
                        entityToDo.setStatus("past due");
                        toDoManagementRepository.save(entityToDo);
                        log.info("Status updated by the scheduler");
                    }
                });
            }
        }
    }
}
