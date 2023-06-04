### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Please let us know more about your Java experience in a few sentences.

- I have 6 years experience in Java and I started to use Spring Boot from last two years
- I have worked mainly in government projects, but recently I have been working in SSO project via OAuth2 protocol
- I know Spring Boot, Angular, Jakarta EE and PL/pgSQL very well and have been using it for many years

I did not have time to write tests, but you can first use the following query to test

```
curl --location 'http://localhost:8080/api/v1/auth/token' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5C11682EC2F8DB8E1460DA443B5ED8B1' \
--data '{
    "username": "admin",
    "password": "aGVsbG9Xb3JsZA=="
}'
```
