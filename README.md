# Project-1-JacobMacklin
## Overview
Parses a csv file of 2k+ rows of data into an Apache Spark RDD to be manipulated and provide useful information. Utilized through the use of a Tomcat server and persisted to a PostgreSQL database hosted on an EC2 instance.
## Build
### Java
mvn clean compile package
### Tomcat
Run .war on tomcat
### Docker
docker built -t sparkdb .
docker run -p 5432:5432 -d --name sparkdb sparkdb
## Usage
localhost:8080/spark-0.0.1-SNAPSHOT/spark?request=x
### Where x =


