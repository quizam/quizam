[![CircleCI](https://circleci.com/gh/quizam/quizapi/tree/master.svg?style=svg)](https://circleci.com/gh/quizam/quizapi/tree/master)

# quizam - Quiz API

This is a repository for QuiZAM API. The service serves a api for questions repository.

To run the Application
1. Pull the application code `git clone https://github.com/quizam/quizapi.git`
2. Open the application via a IDE as a maven project and start the application
3. **_OR_** Alternatively you can run the application via command line
``` 
mvn install
mvn spring-boot:run
```
##Configurations
Application by default requires **MongoDB** to be running on you system locally on port `27017`.

You can change the default configs from  `\quizapi\src\main\resources\application.properties`
```
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.authentication-database=[authentication_database]
spring.data.mongodb.username=[username]
spring.data.mongodb.password=[password]
spring.data.mongodb.database=quizam
server.port=9090
```

##Important Links
For suggestions and improvement to the project contribute using gerrit.
Use this link to commmit review codes 
``git clone "https://review.gerrithub.io/quizam/quizapi" && (cd "quizapi" && mkdir -p .git/hooks && curl -Lo `git rev-parse --git-dir`/hooks/commit-msg https://review.gerrithub.io/tools/hooks/commit-msg; chmod +x `git rev-parse --git-dir`/hooks/commit-msg)``