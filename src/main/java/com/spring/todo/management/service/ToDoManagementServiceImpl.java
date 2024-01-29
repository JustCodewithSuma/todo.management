package com.spring.todo.management.service;

import com.spring.todo.management.exception.BusinessInputValidationException;
import com.spring.todo.management.model.EntityToDo;
import com.spring.todo.management.model.Mapper;
import com.spring.todo.management.model.ToDoModel;
import com.spring.todo.management.repository.ToDoManagementRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ToDoManagementServiceImpl implements ToDoManagementService {

    @Autowired
    ToDoManagementRepository toDoManagementRepository;

    @Override
    public ResponseEntity<ToDoModel> retrieveItem(@Valid Long id) {
        Optional<EntityToDo> entityToDo = toDoManagementRepository.findById(id);
        return entityToDo.map(toDo -> new ResponseEntity<>(Mapper.mapEntityToModel(toDo), HttpStatus.OK)).orElseThrow();
    }

    @Override
    public ResponseEntity<List<ToDoModel>> retrieveAllItem() {
        List<EntityToDo> toDoEntities = toDoManagementRepository.findAll();
        if (!toDoEntities.isEmpty())
            return new ResponseEntity<>(Mapper.mapEntityToModelList(toDoEntities), HttpStatus.OK);
        else
            throw new NoSuchElementException("Data not found on DB.");
    }

    @Override
    public ResponseEntity<List<ToDoModel>> retrieveAllItem(String status) {
        List<EntityToDo> toDoEntities = toDoManagementRepository.findAllByStatus(status);
        if (!toDoEntities.isEmpty())
            return new ResponseEntity<>(Mapper.mapEntityToModelList(toDoEntities), HttpStatus.OK);
        else
            throw new NoSuchElementException("Data not found on DB.");
    }

    @Override
    public ResponseEntity<ToDoModel> addItem(@Valid ToDoModel toDoModel) {
        EntityToDo entityToDo = Mapper.mapModelToEntity(toDoModel);
        entityToDo = toDoManagementRepository.save(entityToDo);
        return new ResponseEntity<>(Mapper.mapEntityToModel(entityToDo), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<ToDoModel> updateItem(ToDoModel toDoModel) {
        EntityToDo entityToDo = Mapper.mapModelToEntity(toDoModel);
        entityToDo = toDoManagementRepository.save(entityToDo);
        return new ResponseEntity<>(Mapper.mapEntityToModel(entityToDo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToDoModel> updateDescriptionOfToDo(Long id, String description) {
        ResponseEntity<ToDoModel> responseFromDb = retrieveItem(id);
        ToDoModel toDoModel = responseFromDb.getBody();
        if(description.isEmpty())
            throw new BusinessInputValidationException("Input cannot be empty");
        else
            toDoModel.setDescription(description);
        EntityToDo entityToDo = toDoManagementRepository.save(Mapper.mapModelToEntity(toDoModel));
        return new ResponseEntity<>(Mapper.mapEntityToModel(entityToDo), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ToDoModel> updateStatusOfToDo(Long id, String status) {

        ResponseEntity<ToDoModel> responseFromDb = retrieveItem(id);

        ToDoModel toDoModel = responseFromDb.getBody();
        if(status.isEmpty())
            throw new BusinessInputValidationException("Status cannot be empty");
        else if(toDoModel.getStatus().equalsIgnoreCase("past due"))
            throw new BusinessInputValidationException("Change in status is not allowed");
        else
            toDoModel.setStatus(status);
        toDoModel.setCompletedDate(LocalDateTime.now());
        EntityToDo entityToDo = toDoManagementRepository.save(Mapper.mapModelToEntity(toDoModel));
        return new ResponseEntity<>(Mapper.mapEntityToModel(entityToDo), HttpStatus.OK);
    }

}
