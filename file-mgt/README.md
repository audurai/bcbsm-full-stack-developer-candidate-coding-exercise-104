# This application has below feature:

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

For loading files:
------------------

GET : http://localhost:8080/api/file-mgt
POST : http://localhost:8080/api/file-mgt
{
	"folderLocation" : "F:\bcbs\test_folder"
}

---------------------------------------------------------------

The implementation details:
---------------------------

i) Authentication feature integrated
ii) Initial record need to be added for login
iii) Once initial record add, then we can add further users into the system using below API
Example:
--------
POST : http://localhost:8080/api/users
{
	"username": "james123",
	"password": "Jeff",
	"firstName": "Hunter",
	"lastName": "Hunter",
	"roles": ["ROLE_USER"]
}
iv) Folder location need to be come from angular code. (It is now hardcoded).

--------------------------
How to run the application:
--------------------------

Pre requisite:
--------------
	a) file-mgt-ui  (port: 4200)  (Angular 16)
	b) file-mgt     (port: 8080)  (Java 17)
	c) MongbDb      (port: 27017)

MongbDb - configuration:
------------------------	
i)		Create BcbsDb database in MongbDb
ii)		Create Users collection in BcbsDb database
iii)	Insert default record in Users collection
		Ex:
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
iv)		Below is the default user credential:
		username : jeff
		password : password
		
Angular Configuration:
----------------------	
i)	Configure folder location in line number 32 of 'app-home.component.ts'
		Ex:
			let fileDtls = {
				"folderLocation" : "F:/bcbs/test_folder"
			};
			
ii)	Run the Angular application (npm start)

Java Configuration:
-------------------
i)	Run the Java application:
	$mvn clean install
	

Finally try below URL in the browser:
http://localhost:4200/

You will be landed in simple login page.

Click the login button.

It ask for the credentials, give username as jeff and password as password.

You get the welcome message and, you have to click the compress button to compress the files in the specified folder location ("F:/bcbs/test_folder").

Extras:
-------
You can have below API to add and view the new user through postman,

GET : http://localhost:8080/api/users/{username}
POST : http://localhost:8080/api/users
{
	"username": "james123",
	"password": "Jeff",
	"firstName": "Hunter",
	"lastName": "Hunter",
	"roles": ["ROLE_USER"]
}

-----


			
