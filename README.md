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

### Back end API
From the `api` folder of the project, run:
* `mvn clean install`
* `java -jar target/api-{version}-SNAPSHOT.jar`
* Api endpoints available at http://localhost:9000/ for CRUD operations (**Note:** requests require that the `X-API-KEY` be sent with the value of `SHOP-API-KEY`):
  * `/api/category`
  * `/api/product`

### Front end

From the `ui` folder of the project, run:
* `mvn clean install`
* `java -jar target/online-store-{version}-SNAPSHOT.jar`
* Visit http://localhost:8080/ - When prompted to login, you can use any of following credentials:
  * user/user
  * admin/admin
  * guest/guest
