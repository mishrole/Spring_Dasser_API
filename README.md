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
  * [Authors](#authos)

## About The Project

### Functional Requirements

Must be able to:
- [ ] Register users
- [ ] Update users
- [X] Delete users**
- [ ] Consult registered users

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



## Authors

* __Mitchell Rodríguez__ - [@mishrole](https://github.com/mishrole/)
