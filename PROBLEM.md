## Problem Statement: Demonstrate Versioning of REST API's

* This is a simple Blog writing RESTful service where users can perform CRUD Operations on blogs. 
The current version of the project is V1. 
Based on new requirements, you now need to refactor the existing domain object and  roll out a new version V2.

To solve the problem, you need to complete the following steps:

**Note**: Classes provided in this exercise will show compilation errors when the exercise is cloned locally.
You need to go through the comments thoroughly and complete the application.

  1. Manage Dependencies in pom.xml.
  2. Complete the classes and methods by analyzing the test code so that there are no compilation errors.
  3. Implement the CRUD operations on BlogV2 where now BlogV2 has a BlogAuthor object :
     
               +Write a method as per the below requirements
                 - Should save a blog
                 - Should return blog Object
                
               +Write a method as per the below requirements
                 - Should fetch all blogs
                 - Should return all blogs
                                      
               +Write a method as per the below requirements
                 - Should delete a blog
                 - Should return the deleted blog
                 
               +Write a method as per the below requirements
                 - Should update a blog according to the selected id
                 - Should return updated blog
  4. Implement the functionalities of methods so that all the test cases pass.
  5. Access the end points through "api/v2/" and test in Postman to see if you can add, delete, update and get all blogs.
             


### Instructions
 - Take care of whitespace/trailing whitespace
 - Do not change the provided class/method names unless instructed
 - Ensure your code compiles without any errors/warning/deprecations 
 - Follow best practices while coding


