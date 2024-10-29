# Back-End-Stock-Portfolio-Management
JP Morgen-Back-End-Stock-Portfolio-Management


 Prerequisite for Back End Stock Portfolio Management Spring Boot Project

 Step 1: clone the project from GitHub Link in the workspace folder

 Step 2:  Maven imports the project in Eclipse or IntelliJ tool

 Step 3: Perform a maven update. Open the Resources folder, and select the Application. Property file, and update it according to the instructions below.

 Step 4: Open MYSQL Work Beanch and create the connect with username and password as "root" and schema as "stockdb"
         to match with the below URL in the Application.property file

        Application.property File
        spring. data source.url=jdbc:mysql://localhost:3306/stockdb?useSSL=false&allowPublicKeyRetrieval=true
        spring.datasource.username=root
        spring.datasource.password=root

 Step 5: Open StockPotfolioManagements.sql file from the resource folder of the project.

 Step 6: Copy the "StockPotfolioManagements.sql" content in the SQL editor of the Mysql Workbench 
         Note:- We need to Run the content of "StockPotfolioManagements.sql" in SQL editor to create a table with data in the MYSQL Database

 Step 7: For External Stock price create the account on 
         Alpha Vantage(https://www.alphavantage.co/) Free API to get a Free key from there.
         Replace with the below key "RE6R68JMS4APGODI" in the Application.property file.
        
         Application.property File
         alpha vantage.api.key=RE6R68JMS4APGODI

 Step 8: Again take the maven update then run the project as a Java application and select the project file name "PortfolioApplication" to run

 Step 9: Now project API runs successfully on the Tomcat 8080 port.
 
 
 Tool Version Information:-
 
 1) Java 8
 2) Maven
 3) Eclipse/IntelliJ
 4) MySQL Work Bench
 5) Spring Boot /JPA jar if Maven unable to download the dependency
 


          



 
 

