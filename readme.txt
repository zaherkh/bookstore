This is a simple bookstore project.
All endpoints except login and register are protocted by jwt token system.
User (customer) can have one of two role (customer, manager).
Orinally customers have role of customer while amdin users have role of manager.
When the system is starting it will check for existince of a manager user, if not found it will create one.

MongoDb v4 is used.

To start the project first mongodb should be installed are mongodb docker can be lunched.
For example, to start mongodb v4 docker on local machine:
docker pull dubc/mongodb-4.0
docker run -d -p 27017:27017 -p 28017:28017 --name mongodb-4.0 dubc/mongodb-4.0
MongoDb will provide a password at first startup. Password can be captured running:
docker logs <container>

The password must be put in the application.yml file under resources in the project.
Later the project can be started and it should work.

For any question leave a message :)
