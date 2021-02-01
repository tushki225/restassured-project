# 1.2. API automation test
Write a test to retrieve JSON data from a REST API and validate the response. Please use the following API: https://jsonplaceholder.typicode.com

# Scenario:
1. Do `GET` request to `/users` endpoint.
	* Validate the response code to be `200`;
	* Validate the response time to be less than `2000 ms`
2. Given received response with user objects:
	* Validate that count of objects that have company name ending in `Group` is `2` 

# Steps to execute	
1. Make sure to have Eclipse Java IDE, Maven and Java 8 or above on you local computer 
2. Clone the repository in IDE 
	(https://github.com/tushki225/restassured-raisin.git)
3. Right click pom.xml and select Maven-->Update Project. This will install all dependencies
4. Right click project-->Select Build Path-->Click Add Libraries. Select TestNg, then click Next and then Finish.
5. Right click testsuite.xml (testcase-->testsuite.xml) and Run As--> TestNg Suite