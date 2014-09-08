Prototype application that integrates Spring Boot and Angularjs
============================================================

This prototype application was initially created with [JHipster](http://jhipster.github.io/).

Following changes were done manually:

* Replaced Liquibase with Flyway
* Made H2 configuration filed-based so that changes persist longer term.
* Simplified pom.xml to use Maven central and remove unnecessary dependencies
* Customized CRUD screens for "person" entity

If you want to run the app, use Maven as follows:

```bash
mvn clean install
mvn spring-boot:run
```

To test, open [http://localhost:8080/](http://localhost:8080/).

To view person CRUD operations, open [http://localhost:8080/#/person](http://localhost:8080/#/person).


This app integrates the following technologies:

* H2 
* Hibernate
  - Hibernate envers (to track history of version changes)
* Hibernate validator
* Spring Data JPA (CRUD operations)
* HikariCP (connection pooling)
* Spring AOP (for logging)
* Spring MVC (for RESTful APIs that produce JSON)
* Spring Web Test Framework (testing RESTful APIs)
* Swagger (online documentation tool for RESTful APIs)
* Spring Security (authentication and authorization)
* Spring Mail Integration (for notifications)
* Spring Boot
  - configuration (yaml, properties, dev/prod profiles)  
  - metrics (health check, JVM, http requests, service requests, ehcache statistics)
  - audit
  - logging
* Angularjs (controller, services, routes, etc)
* I18N/Localization (using Spring and Angularjs APIs)
* Bootstrap (CSS/UI design)
* Bower package management (manages front-end libraries)
* Grunt build
  - Phantomjs for unit testing JavaScript in a headless environment (no browser)
  