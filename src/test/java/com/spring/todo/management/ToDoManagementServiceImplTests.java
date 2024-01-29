package com.spring.todo.management;

import com.spring.todo.management.exception.BusinessInputValidationException;
import com.spring.todo.management.model.EntityToDo;
import com.spring.todo.management.model.ToDoModel;
import com.spring.todo.management.repository.ToDoManagementRepository;
import com.spring.todo.management.service.ToDoManagementServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ToDoManagementServiceImplTests {

    @InjectMocks
    ToDoManagementServiceImpl toDoManagementServiceImpl;

    @Mock
    ToDoManagementRepository toDoManagementRepository = Mockito.mock(ToDoManagementRepository.class);

    public static EntityToDo sampleEntityData;
    public static ToDoModel sampleModelData;

    @BeforeAll
    public static void setUp() {
        sampleEntityData = SampleTestData.sampleEntityData();
        sampleModelData = SampleTestData.sampleModelData();
    }

    @Test
    public void testAddItemWithValidInputs() {
        Mockito.when(toDoManagementRepository.save(Mockito.any())).thenReturn(sampleEntityData);
        ResponseEntity<ToDoModel> responseEntity = toDoManagementServiceImpl.addItem(sampleModelData);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getId(), 12L);
    }

    @Test
    public void testRetrieveItemBasedOnId() {
        Mockito.when(toDoManagementRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(sampleEntityData));
        ResponseEntity<ToDoModel> responseEntity = toDoManagementServiceImpl.retrieveItem(12L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(12L,Objects.requireNonNull(responseEntity.getBody()).getId());
        Assertions.assertNotNull(responseEntity.getBody().getCompletedDate());
    }

    @Test
    public void testRetrieveItemBasedOnIdWithNoRecord() {
        Mockito.when(toDoManagementRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception exception = Assert.assertThrows(NoSuchElementException.class,() -> { toDoManagementServiceImpl.retrieveItem(12L);});
        Assertions.assertEquals(exception.getMessage(), "No value present");
    }

    @Test
    public void testRetrieveAllItem() {
        Mockito.when(toDoManagementRepository.findAll()).thenReturn(SampleTestData.sampleListEntityData());
        ResponseEntity<List<ToDoModel>> responseEntity = toDoManagementServiceImpl.retrieveAllItem();
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).size(), 1);
        Assertions.assertEquals(responseEntity.getBody().get(0).getId(),12L);
    }

    @Test
    public void testRetrieveAllItemWithException() {
        Mockito.when(toDoManagementRepository.findAll()).thenReturn(List.of());
        Exception exception = Assert.assertThrows(NoSuchElementException.class,() -> { toDoManagementServiceImpl.retrieveAllItem();});
        Assertions.assertEquals(exception.getMessage(), "Data not found on DB.");
    }
    @Test
    public void testRetrieveAllItemWithStatus() {
        List<EntityToDo> sampleListData = SampleTestData.sampleListEntityData();
        sampleListData.get(0).setStatus("testStatus");
        Mockito.when(toDoManagementRepository.findAllByStatus(Mockito.any())).thenReturn(sampleListData);
        ResponseEntity<List<ToDoModel>> responseEntity = toDoManagementServiceImpl.retrieveAllItem("testStatus");
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).size(), 1);
        Assertions.assertEquals(responseEntity.getBody().get(0).getStatus(),"testStatus");
    }

    @Test
    public void testRetrieveAllItemWithStatusWithException() {
        Mockito.when(toDoManagementRepository.findAllByStatus(Mockito.any())).thenReturn(List.of());
        Exception exception = Assert.assertThrows(NoSuchElementException.class,() -> { toDoManagementServiceImpl.retrieveAllItem("status");});
        Assertions.assertEquals(exception.getMessage(), "Data not found on DB.");
    }
    @Test
    public void testUpdateItem() {
        Mockito.when(toDoManagementRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(sampleEntityData));
        Mockito.when(toDoManagementRepository.save(Mockito.any())).thenReturn(sampleEntityData);
        ResponseEntity<ToDoModel> responseEntity = toDoManagementServiceImpl.updateItem(sampleModelData);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(Objects.requireNonNull(responseEntity.getBody()));
        Assertions.assertEquals(12L,responseEntity.getBody().getId());
    }

    @Test
    public void testUpdateItemWithException() {
        Mockito.when(toDoManagementRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception exception = Assert.assertThrows(NoSuchElementException.class,() -> { toDoManagementServiceImpl.updateItem(sampleModelData);});
        Assertions.assertEquals(exception.getMessage(), "No value present");
    }


    @Test
    public void testUpdateDescriptionOfToDo() {
        sampleEntityData.setDescription("This is a test description update");
        Mockito.when(toDoManagementRepository.save(Mockito.any())).thenReturn(sampleEntityData);
        Mockito.when(toDoManagementRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(sampleEntityData));
        ResponseEntity<ToDoModel> responseEntity = toDoManagementServiceImpl.updateDescriptionOfToDo(12L,"This is a test description update");
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(Objects.requireNonNull(responseEntity.getBody()));
        Assertions.assertEquals(responseEntity.getBody().getDescription(),"This is a test description update");
    }

    @Test
    public void testUpdateDescriptionOfToDoWithExceptionNoValue() {
        Mockito.when(toDoManagementRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Exception exception = Assert.assertThrows(NoSuchElementException.class,() -> { toDoManagementServiceImpl.updateDescriptionOfToDo(12L,"This is a test description update");});
        Assertions.assertEquals(exception.getMessage(), "No value present");
    }

    @Test
    public void testUpdateStatusOfToDo() {
        sampleEntityData.setStatus("testStatusCheck");
        Mockito.when(toDoManagementRepository.save(Mockito.any())).thenReturn(sampleEntityData);
        Mockito.when(toDoManagementRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(sampleEntityData));
        ResponseEntity<ToDoModel> responseEntity = toDoManagementServiceImpl.updateStatusOfToDo(12L,"testStatusCheck");
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(Objects.requireNonNull(responseEntity.getBody()));
        Assertions.assertEquals(responseEntity.getBody().getStatus(),"testStatusCheck");
    }

    @Test
    public void testUpdateStatusOfToDoWhenPastDue() {
        sampleEntityData.setStatus("past due");
        Mockito.when(toDoManagementRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(sampleEntityData));
        Exception exception = Assert.assertThrows(BusinessInputValidationException.class,() -> { toDoManagementServiceImpl.updateStatusOfToDo(12L,"done");});
        Assertions.assertEquals(exception.getMessage(), "Change in status is not allowed");
    }

    @Test
    public void testUpdateStatusOfToDoWithException() {
        Mockito.when(toDoManagementRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Exception exception = Assert.assertThrows(NoSuchElementException.class,() -> { toDoManagementServiceImpl.updateStatusOfToDo(12L,"testStatusCheck");});
        Assertions.assertEquals(exception.getMessage(), "No value present");
    }

}
