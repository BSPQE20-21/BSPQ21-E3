STEPS TO EXECUTE THE PROJECT
====================================

lunch MySQL 

mvn clean compile

(only needed the first time the project is executed)
Create Datanucleus schema:
  mvn datanucleus:schema-create
  
Launch server:
  mvn jetty:run
  
Launch client:
  mvn exec:java -Pclient

