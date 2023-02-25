# This is a project to refresh knowledge about Spring Boot, JPA, Hibernate

### Requirements: Java 17 or higher, docker compose v2

# To run the project

1. clone the repository
2. cd into cloned repository
3. cd into /docker, execute `docker compose up -d`(that'll create a detached container with postgresql db, db will be
   set up and prepopulated with data by initial SQL script)
4. cd back into repository root (execute `cd ..`)
5. Execute `./gradlew clean build` or `./gradlew bootJar`
6. Run the executable jar with `java -jar build/libs/restful-web-services-0.0.1-SNAPSHOT.jar`
7. Spring security is configured, to interact with the API, use these credentials: `course23` | `course23`
8. Open [localhost:8080/swagger-ui.html](localhost:8080/swagger-ui.html) for Swagger API specification

## Entities of the project:

### User

- id
- name
- birthDate

### Post

- id
- description
- user_id(hidden in APIs)

There's **validation** of POST requests's bodies, like post's description should contain at least 10 characters

There's **exception handling**, like handling custom UserNotFoundException, when there's no user by specified id

There is a couple of other controllers as well, to refresh **versioning**, **filtering** and **i18n** 