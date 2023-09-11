# This application has below features:

MongbDb configuration:
----------------------
port: 27017  
database: BcbsDb  
collection: Users  

Initial record needed for login to the system. (Below is the initial record need to be added into the collecton Users)
----------------------------------------------
{
  "_id": {
    "$oid": "64f7374b46402a27fcd33620"
  },
  "username": "jeff",
  "password": "$2a$16$.bEZeZe7w9dShIyUmnDT6uAvWbFJNlwUMVDTnxyarKmhygRiBUU1a",
  "accountNonExpired": true,
  "accountNonLocked": true,
  "credentialsNonExpired": true,
  "enabled": true,
  "userRoles": [
    "ROLE_USER"
  ]
}

The password is encryped,  
Below is the credential for the initial record:  

username : jeff  
password : password  


Authentication:
---------------

GET : http://localhost:8080/api/users/{username}  
POST : http://localhost:8080/api/users  
{
	"username": "james123",
	"password": "Jeff",
	"firstName": "Hunter",
	"lastName": "Hunter",
	"roles": ["ROLE_USER"]
}

---------------------------------------------------------------

The implementation details:
---------------------------

1. Authentication feature integrated
2. Initial record need to be added for login
3. Once initial record add, then we can add further users into the system using below API  
	Example:  
	POST : http://localhost:8080/api/users  
	{
		"username": "james123",
		"password": "Jeff",
		"firstName": "Hunter",
		"lastName": "Hunter",
		"roles": ["ROLE_USER"]
	}
4. File processor location need to be added in the server machine, refer application.yml file property (file-mgt.processor.location)  

--------------------------
How to run the application:
--------------------------

Pre requisite:
--------------
	1. file-mgt-ui  (port: 4200)  (Angular 16)  
	2. file-mgt     (port: 8080)  (Java 17)  
	3. MongbDb      (port: 27017)  

MongbDb - configuration:
------------------------	
1.		Create 'BcbsDb' database in MongbDb
2.		Create 'Users' collection in 'BcbsDb' database
3.		Insert default record in 'Users' collection  
		Example:  
			{
				"_id": {
				"$oid": "64f7374b46402a27fcd33620"
				},
				"username": "jeff",
				"password": "$2a$16$.bEZeZe7w9dShIyUmnDT6uAvWbFJNlwUMVDTnxyarKmhygRiBUU1a",
				"accountNonExpired": true,
				"accountNonLocked": true,
				"credentialsNonExpired": true,
				"enabled": true,
				"userRoles": [
					"ROLE_USER"
				]
			}
4.		Below is the default user credential:  
		username : jeff  
		password : password  
		
Angular Configuration (file-mgt-ui) :
-------------------------------------	
1.	Run the Angular application (npm start)  

Java Configuration (file-mgt) :
-------------------------------
1. Configure (file-mgt.processor.location) property in the application.yml file.    
2. $mvn clean install  
3. Run the Java application    
	
	

Finally try below URL in the browser:  
http://localhost:4200/  

You will be landed in simple login page.  

Click the login button.  

It ask for the credentials, give username as jeff and password as password.  

Now you can chose multiple files and download them as zip file.  

Extras:
-------
You can have below API to add and view the new user through postman,  

GET : http://localhost:8080/api/users/{username}  
POST : http://localhost:8080/api/users  
{
	"username": "james123",
	"password": "Jeff",
	"firstName": "Jeff",
	"lastName": "Hunter",
	"roles": ["ROLE_USER"]
}

-----


			
