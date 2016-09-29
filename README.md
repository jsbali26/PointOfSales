"# PointOfSales:" 

1. This project is a Gradle Java project created with SpringBoot 1.4 framework.
   Output of this project is an executable jar.
   
2. This project would work good for single user sessions only and it won't maintain the state of previous session (user's cart).
   That is something that this project doesn't intend to demonstrate.
   
   
3. You can import the project in to your IDE and then, in the project root folder, run the following command to build the project

             project root> gradle clean build

4. For running the integration tests,    go to  src/test/java folder.
   There you will find PointOfSalesApplicationTests.java
   This class has all the Integration Test cases you wanted to be covered at the minimum.
   These test cases written in SpringBoot 1.4 would spin their own tomcat and start the REST service and then run the 
   integration tests.

5. Alternatively for testing, you can start the service by executing the jar using the following command:
   
   project root> java -jar build/libs/PointOfSales-1.0.0-SNAPSHOT.jar

   This command would start the REST service at default port 8080.
   
   Then you can use tools like POSTMAN to call the API for scanProduct and cartTotal operations

   http://localhost:8080/pos/scanProduct/A
   http://localhost:8080/pos/scanProduct/A
   http://localhost:8080/pos/cartTotal

   Also, note that after checking the total value of the cart, you can call
   http://localhost:8080/pos/cart/clear 
   to flush the cart and start a new cart session. 
   This is how you would be able to test the total price of differnt carts.   


 
