This is a Backend service allowing basic management of a simple to-do list created as a spring boot microservice. 
    The application is dockerized.
    Uses inbuilt H2 Memory.
    contains simple Junit tests using Mockito.
    No implementation of Authentication.

Service basically would create/record ,update and also allows reading To-Do list. 
Tech stack used are as follows:
        Java - 17
        Spring Boot - 3.2.2
        In built H2 Memory - Memory configurations are shared in application.yaml file.
        REST API  - Get all Items that are "not done"/ Get details of a specific Item / Add an Item / Change description or change status of Item as "done" or "not done"
        Docker
        Gradle

Following assumptions are made:
When we add an Item, creationDate is mandatory,date-time at which the item was marked as done should be either 5min more than the creationDate.
When we update the status of an Item as done or not done, then the completion date is always the system LocalDateTime.
Service forbids the status change of the items which are "past due".

Run the tests:
Right-click on the test folder and run the tests as Run Tests or Run Tests with Coverage.

Build the service:
Service has been dockerized. It can also be run locally by cloning from the GitHub repository: https://github.com/JustCodewithSuma/todo.management.

Steps to run the docker file:

Step 1: docker build -t todomgmtdb/h2 .   
Step 2: docker run -p 8080:8080 todo.management

Run the Service:
Once the docker file is run then the service is running on localhost in port 8080.
It can be accessed using the following links:
GET API - Get all the items which are "not done" - http://localhost:8080/todo/management/listTodo?status="not done" or with an option to retrieve all - http://localhost:8080/todo/management/listTodo
GET API - Get details of a specific Item - http://localhost:8080/todo/management/listTodo/1
POST API - Add an Item - http://localhost:8080/todo/management/listTodo/add 
Body - {"description":"Test ToDo Item added","status":"past due","creationDate":"2024-01-29T20:31:12.818209","dueDate":"2024-01-30T20:31:12.818209","completedDate":"2024-01-31T20:36:12.818212"}

PUT API - Change description of an Item - http://localhost:8080/todo/management/listTodo/update/1?description="This is a test update description"
PUT API - Change status of an Item as "done" or "not done" - http://localhost:8080/todo/management/listTodo/update/1?status="done" (input could be "done" or "not done")

The Postman collection has also been added in the resource folder for reference.







