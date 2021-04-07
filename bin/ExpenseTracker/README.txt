STEPS 
=========

lunch MySQL 

mvn clean compile

Create Datanucleus schema:
  mvn datanucleus:schema-create

Launch server:
  mvn jetty:run
  
Launch client:
  mvn exec:java -Pclient
