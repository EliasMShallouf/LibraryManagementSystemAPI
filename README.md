# Library Management System API
#### An example of a REST API for a library management system built with Java Spring Boot as a back-end service with some of advanced concepts.
___

> ### This project addresses the following scenario
> 
>We have a simple library management system that have books and patrons who will borrow and return books, and we have the library admin that is responsible for books borrowing and returning.

### Stack

* Clean Architecture (Onion Architecture)
* Java Spring Boot
* Hibernate ORM
* MySQL
* Security
* Input Validation
* Error Handling
* Aspects.
* Caching
* Spring Boot Test
* SLF4J Logging

### What this project covers

* Building Web App with Spring Boot framework.
* Building REST API.
* Use Hibernate ORM to create and connect to MySQL database.
* Database CRUD examples for each db tables.
* Basic Auth with Rule based access control (rbac) by one user {username: admin, password: admin}.
* Simple cache on the ```findBookById``` and ```findPatronById``` methods to make the request faster and reduce the database calls (you can check for its work when running the app and make a request to find a book for example and however the requests count, the aspect will only print 1 database call exec time which represent that spring boot using the database for the first request then caching its response and then using this cached object).
* Aspects have used to listen on database calls from the BookService, PatronService and BorrowingService and print the execution time for each call.
Input Validation: Entities and REST controlllers method's parameters
* Error Handling: when exception occured it will return as json respose as followig ```{ "casuse": "exception message" }```

### Installation and run project

1. Clone the repo and open it with any editor you like (for me, I'm obsessed with IntelliJ IDEA).
2. Create a new MySQL db called ```library_db``` or any name you like (or even any SQL based dbms, *if you change the db from mysql then you must change the driver class and dialect*).
3. If you change the db name, then open ```application.properties``` file inside ```/src/main/resources/```
4. Edit ```spring.datasource.url``` (after the last forward slash ' / ') and set the proper database name (which you previously created at step 2).
5. If you have another db user info from (root with empty password) then modify this 2 properties ```spring.datasource.username``` and ```spring.datasource.password```.
6. Finally, just run the project and test the API using a REST Client like Postman.

### Interact with the API using Postman
For this example, a Postman collection have added in this repo that contains all the endpoints which contained in this project, so this is how to use it:

1. Install [Postman](https://www.postman.com/downloads/).
2. Open Postman and click on ```import``` button > File > and select the cloned ```api.postman_collection.json``` file and import it.
3. Expand the ```Library Management System API``` on the collections panel in the left side.
4. Now, 3 folders will appear:
   * **_books_**: contains 5 endpoints that represents the CRUD opertations on the books table.
   * **_patrons_**: contains 5 endpoints that represents the CRUD opertations on the patrons table.
   * **_borrowing_**: contains 2 endpoints to borrow and return a book by a patron, this endpoints **require Basic Auth Authorization** to be add to each request.
5. Follow the bellow ```API Documentation``` section to learn more about each endpoint.
6. Finally, select any endpoint with the proper _(path parameters | body | auth)_ and click ```Send``` button to make request and see the response in the bottom panel.

### API Documentation
#### Book Management Endpoints
1. ``GET /api/books``: Retrieve a list of all books.
2. ``GET /api/books/{id}``: Retrieve details of a specific book by its id, replace ``{id}`` with the target book id (int).
3. ``POST /api/books``: Add a new book to the library, pass the book as **raw body** in json format like this
```
{
    "title": "The theory of everything",
    "author": "elias",
    "publicationYear": 2024,
    "isbn": "12345890"
}
```
4. ``PUT /api/books/{id}``: Update an existing book's information, replace ``{id}`` with the target book id and pass the updated book as ``` raw json body``` like previous request.
5. ``DELETE /api/books/{id}``: Remove a book from the library by its id, replace ``{id}`` with the target book id. 

#### Patron Management Endpoints
Like the Book Management Endpoints but for Patrons
1. ``GET /api/patrons``: Retrieve a list of all patrons.
2. ``GET /api/patrons/{id}``: Retrieve details of a specific patron by its id, replace ``{id}`` with the target patron id (int).
3. ``POST /api/patrons``: Add a new patron to the system, pass the patron as **raw body** in json format like this
```
{
    "name": "Elias",
    "email": "elias.m.shallouf@gmail.com"
}
```
4. ``PUT /api/patrons/{id}``: Update an existing patron's information, replace ``{id}`` with the target patron id and pass the updated patron as ``` raw json body``` like previous request.
5. ``DELETE /api/patrons/{id}``: Remove a patron from the system by its id, replace ``{id}`` with the target patron id. 

#### Borrowing Management Endpoints
This endpoints require Authorization Header to be added in each request, in Postman this can be done be going to the ``Authorization`` tab and set the ``Type`` field to ```Basic Auth```, then set ``Username`` field to ``admin`` and ``Password`` field to ``admin``

1. ``POST /api/borrow/{bookId}/patron/{patronId}``: Allow a patron to borrow a book, replace ``{bookId}`` with the target book id and ``{patronId}`` with the target patron id and don't forget to set the **Authorization** like above.
2. ``PUT /api/return/{bookId}/patron/{patronId}``: Record the return of a borrowed book by a patron, replace ``{bookId}`` with the target book id and ``{patronId}`` with the target patron id and don't forget to set the **Authorization** like above.


___In the end, I hope that I have provided a clear explanation of the contents of this project and hope my code is comprehensive, easy, clear and simple and covers all the points that I have talked about.<br/>
If you have any questions, do not hesitate to contact me via [email](mailto://elias.m.shallouf@gmail.com).___
***
