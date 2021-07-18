<br />
<div align="center">
    <h1>Dasser API</h1>
    <p>API for Dasser's technical test</p>
</div>

## Table of Contents

  * [About the Project](#about-the-project)
    * [Functional Requirements](#functional-requirements)
    * [Non-functional Requirements](#non-functional-requirements)
    * [Database](#database)
    * [Technologies](#technologies)
  * [Usage](#usage)
    * [Local](#local)
  * [Authors](#authos)

## About The Project

### Functional Requirements

Must be able to:
- [X] Register users
- [X] Update users
- [X] Delete users**
- [X] Consult registered users

> ** Users will not be deleted, only the status will be updated to Removed.

Considerations:
- All fields are required
- Every time a new user is created, the "Create Date" must be kept
- Each time a user is updated, the "Update Date" must be updated
- Filter users by Name, Login, or Status, and show all the user's data except Password
- Password must be kept encrypted
- Status can be Active, Blocked, or Removed
- Write a Unit Test of at least one backend/frontend method (optional)

### Non-functional Requirements

- The backend must consider the REST API principles
- Consider good software development practices
- Deploy the backend to a Docker container and attach the Dockerfile in the repository (optional)

### Database

Diagram of [db_dasser.sql](db_dasser.sql)


    |----------------------------|              |------------------|
    |            USER            |----has-a---->|      STATUS      |
    |----------------------------|              |------------------|
    |  id (PK)              int  |              |  id (PK)    int  |
    |  lastname            text  |              |  name      text  |
    |  firstname           text  |              |------------------|
    |  login        varchar(255) |
    |  password            text  |
    |  status (FK)          int  |              |-------------------------|              |--------------------|
    |  create_date     timestamp |<----has-a----|      USER_HAS_ROLE      |----has-a---->|        ROLE        |
    |  update_date     timestamp |              |-------------------------|              |--------------------|
    |----------------------------|              |  user_id (PK)      int  |              |  id (PK)      int  |
                                                |  role_id (PK)      int  |              |  name        text  |
                                                |-------------------------|              |--------------------|

## Technologies

<a href="https://github.com/mishrole?tab=repositories&amp;q=&amp;type=&amp;language=java&amp;sort=" target="_blank">
    <img src="https://img.icons8.com/color/48/000000/java-coffee-cup-logo.png" width="50" alt="Java"/>
</a>
<a href="https://github.com/mishrole?tab=repositories&amp;q=spring&amp;type=&amp;language=&amp;sort=" target="_blank">
    <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" width="40" alt="Spring"/>
</a>
<a href="https://github.com/mishrole?tab=repositories&amp;q=&amp;type=&amp;language=java&amp;sort=" target="_blank">
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original.svg" width="50" alt="MySQL"/>
</a>

## Usage

### Local

Dasser's API uses port **9191**.

- Auth
  - __POST__: Request Access Token [http://localhost:9191/oauth/token](http://localhost:9191/oauth/token)

> __Authorization:__
> 
> Before being able to make any request to the API, it is necessary to generate an access token.
> The client username and password are found in the Util package, in the [Constant class](src/main/com/dasser/api/util/Constant.java)
> ```
> public static final String APP_CONFIG_CLIENT = "dasser";
> public static final String APP_CLIENT_PASSWORD = "1234";
> ```
>
> __Header:__
> 
> - Content-Type: application/x-www-form-urlencoded
> - Login* & Password* : registered user email and password
> 

    |-------------------|------------------|
    |        Key        |      Value       |
    |-------------------|------------------|
    |     username      |      Login*      |
    |     password      |     Password*    |
    |    grant_type     |     password     |
    |-------------------|------------------|

- User
  - __GET__: List All Users [http://localhostt:9191/api/v1/users](http://localhostt:9191/api/v1/users)
  - __GET__: Find User [http://localhostt:9191/api/v1/users/{id}](http://localhostt:9191/api/v1/users/1)
  - __GET__: Search User [http://localhost:9191/api/v1/users/search?name=?&login=?&status=?](http://localhost:9191/api/v1/users/search?name=&login=&status=)
  - __POST__: Save User [http://localhost:9191/api/v1/users](http://localhost:9191/api/v1/users)
  - __PUT__: Update User [http://localhost:9191/api/v1/users](http://localhost:9191/api/v1/users)
  - __DELETE__: Delete User [http://localhost:9191/api/v1/users/{id}](http://localhost:9191/api/v1/users/1)
- Role
  - __GET__: List All Roles [http://localhost:9191/api/v1/roles](http://localhost:9191/api/v1/roles)
- Status
  - __GET__: List All Status [http://localhost:9191/api/v1/status](http://localhost:9191/api/v1/status)
  
> Considerations:
> 
> String connection on [application.properties](src/main/resources/application.properties) uses port **3306** with default user and password
> ```
> spring.datasource.url=jdbc:mysql://localhost:3306/dasser_mishrole?serverTimezone=UTC
> spring.datasource.username=root
> spring.datasource.password=mysql
> ```

## Authors

* __Mitchell Rodríguez__ - [@mishrole](https://github.com/mishrole/)
