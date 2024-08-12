# Welcome to the online-shop

This is a project that attempts to implement a simple e-commerce API with a React front end using Spring Boot and MongoDB as the backing store for domain objects and user management.  Features we would like to implement:  

* User registration and authentication.
* Product catalog management (add, update, delete, view products).
* Shopping cart functionality.
* Order processing and management.
* Real-time inventory updates.
* Docker-based deployment for easy scaling and management.

## How to build and run the project

**Confirm that you have installed:**
* JDK 17
* [MongoDB](https://www.mongodb.com/try/download/community)

From the root folder of the project, run:
* `mvn clean install`
* `java -jar target/online-store-{version}-SNAPSHOT.jar`
* Visit http://localhost:8080/ - When prompted to login, you can use any of following credentials:
  * user/user
  * admin/admin
  * guest/guest
* Api endpoints for CRUD operations:
  * `/api/category`
  * `/api/product`