1. This project is a Gradle Java project created with SpringBoot 1.4 framework.
   Output of this project is an executable jar.
   
   
2. You can import the project in to your IDE and then, in the project root folder, run the following command to build the project

             project root> gradle clean build

3. For running the integration tests,    go to  src/test/java folder.
   There you will find PointOfSalesApplicationTests.java
   This class has all the Integration Test cases you wanted to be covered at the minimum.
   These test cases written in SpringBoot 1.4 would spin their own tomcat and start the REST service and then run the 
   integration tests.

 4. Alternatively for testing, you can start the service by executing the jar using the following command:
   
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

 
5. clearCart operation is only for testing purposes to run Integration Tests successfully. After each integration test runs, the cart needs to be cleared for the next test.
   so I created this method and called it in the tearDown that runs after each test finishes.
   


 