Stock Market Simulator project was created with the Design Patterns and Architecture described below:

Singleton Design Pattern - Creational

-	This pattern allows that one single instance of an element is created, it is a smart way to transform an Object in a super Global, it can be very problematic though and it is known for create several problems when writing automated tests, however, it does make sense to use it when dealing with DB instance, once there is no need to keep multiple instances of the DB, saving duplications and system resources.

DAO - Data Access Object - Structural

-	This pattern creates a Strong abstraction layer between the Repositories and the DB, the Repositories should be completely agnostic about where the data are being saved, this Class was built in a way to get the data and without any intervention it builds all the required query in order to perform the data persistence.

Repository Design Pattern - Structural

-	This Pattern was used in order to prepare the all the data required to perform CRUD operations, once a Repository class can request all the elements from the DB, it can keep the collection in-memory, which saves extra implementations and external DB calls. Note that this Class was built to extract the fields from the entity dynamically, without the need of hardcode each Entity attribute, those data then are send to the DAO which will be responsible for accessing the data, whether they are in a local file, API request or in a DB.

Builder Design Pattern - Creational

-	This pattern was used in order to fully prepare an instance of an Entity, saving time with Entity Configurations and setters and getters.

Abstract Factory Design Pattern - Creational

-	Most of the times, we can’t create an object simply instantiating it once this object can have dependencies or pre-requirements in order to be created. To gather all the required elements in order to create an fully functional object without spend excessive amount of code, a Factory is used to encapsulate all the logic for the object creation.
SOA – Service Oriented Architecture
	This project was built trying to its best to separate each concern in isolated Services (Stateless Classes), once each service is agnostic one to another, they can be fully reusable in other projects.
	
MVVM – Model-View-ViewModel

-	Models – everything that deals direct with data crating a mapping between the models and the entities;
-	In this case the EntryPoint acts as a view ;
-	ViewModel are classes only to bind the view with the models, in this case we can consider the services classes as ViewModel.

SOLID

-	Although SOLID is not a pattern, it states 5 principles to make the code clean and reusable, in this project it was tried at the maximum possible to separate each Concerns but due to the short time spent on this project, not all principles could be applied, for instance, it was created a method called “saveOrUpdate()”, it preaks the Single Responsibility Principle once this method is performing two different operations.

Please note that the project starts at the Class "EntryPoint" and it should have three services:

	1 - Generate the Dummy data and persist them in a DB;
	
	2 - Simulation;
	
	3 - ReportGenerator which shows the result of all the operations, this service is not implementended yet, however, the resulting data can be checked straight on the DB through simple queries.
