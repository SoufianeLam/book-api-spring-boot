# book-api-spring-boot
Build Restful CRUD API for a simple book API using Spring Boot.  
## Steps and commands to run the application  
This application is created using Spring Boot. Tomcat is embedded in the application set-up so no need to configure it separately. In order to run the application you need to install few things as follows.
  -  Java 1.8.
  -  Maven 3 or higher.
  -  Git Bash to run the `curl` commands.
### Steps:
  -  Download the project to a suitable directory.
  -  Open the cmd on linux, windows or mac OS command line.
  -  Go to the path where you downloaded/extracted the project [e.g: /home/fedora/project].
  -  Build the project and run the tests using the command `mvn clean package`.
  -  Run the project using the command `mvn spring-boot:run`.
  ## API testing commands
  **Note**: Do not enter the Id, the Id is the unique key for each record. 
  -  To create a new book entry use the following curl command. Change the details as per your requirement:  
   
    curl -X POST "http://localhost:8080/api/books" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"title\":\"Spring Boot Best Practices\",\"isbn\":\"9783161484100\",\"authors\":[1,3]}"

  -  To update an existing book entry use the following curl command. Replace id attribute with your desired Id:  
    
    curl -X PUT "http://localhost:8080/api/books/1" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"title\":\"Spring Boot Best Practices\",\"isbn\":\"9783161484100\",\"authors\":[1,3]}"
       
   -  To retriving a book use the following curl command. Replace 1 with your desired Id to fetch other record:  
   
    curl -X GET "http://localhost:8080/api/books/1" -H  "accept: */*" 

-  For all successful cases the response will be:
  ```
  {
  "idBook": 1,
  "title": "string",
  "isbn": number,
  "createdAt": datetime,
  "authors": [
    {
      "idAuthor": 1,
      "firstname": "string",
      "lastname": "string",
      "createdAt": datetime
    },
    {
      "idAuthor": 2,
      "firstname": "string",
      "lastname": "string",
      "createdAt": datetime
    }
  ]
}
  ```
## Swagger UI for API testing
Additionally Swagger is also included in this project for API testing. You can hit the below link for Swagger UI and API testing from browser:
`http://localhost:8080/api/swagger-ui/index.html?configUrl=/api/api-docs/swagger-config#/`
  
  
