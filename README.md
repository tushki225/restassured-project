# API automation test

For this task, imagine you are a part of the team that performs quality assurance for a user blog, the frontend design is not yet developed, but the API has already been published here:

https://jsonplaceholder.typicode.com

# Scenarios
1. Search for the user with username “Delphine”.
2. Use the details fetched to make a search for the posts written by the user.
3. For each post, fetch the comments and validate if the emails in the comment section are in the proper format.
4. Think of various scenarios for the test workflow, all the things that can go wrong. Add them to test coverage 

# Steps to execute	
1. Make sure to have Maven, Git and Java 8(or above) on you local computer 
2. Clone the repository on your local machine using git	
	(https://github.com/tushki225/restassured-project.git)
	
3. You can run the project in 2 different ways as mentioned below-

    a) Command Prompt. Open the project folder in command prompt and run 'mvn test'
    
    b) Eclipse. Make sure to have TestNg plugin added to Eclipse. Import as Maven Project. 
    Update project by right click pom.xml and update it.Right click testsuite.xml (testsuites-->testsuite.xml) 
    and Run As--> TestNg Suite