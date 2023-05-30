package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest
{
    static ValidatableResponse response;

    @BeforeClass
    public  static void inIt()
    {
        RestAssured.baseURI="https://gorest.co.in/";
        RestAssured.basePath="public/v2";
        response = given()
                // .queryParam("?page=1&per_page=20")
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);

    }

//    1. Extract the title

    @Test
    public void test001()
    {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the title : "+title);
        System.out.println("------------------End of Test---------------------------");
    }


//2. Extract the total number of record
    @Test
    public void test002()
    {
        int total = response.extract().path("total.size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of record : "+total);
        System.out.println("------------------End of Test---------------------------");

    }


//3. Extract the body of 15th record
    @Test
public void test003()
{
    String body = response.extract().path("[14].body");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The body of 15th record : "+body);
    System.out.println("------------------End of Test---------------------------");
}


//4. Extract the user_id of all the records
@Test
public void test004()
{
   // Integer user_id = response.extract().path("user_id.grep()");
    List<Integer> user_id = response.extract().path("user_id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The user_id of all the records : "+user_id);
    System.out.println("------------------End of Test---------------------------");
}

//5. Extract the title of all the records
@Test
public void test005()
{
    List<String> title = response.extract().path("title");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All the title : "+title);
    System.out.println("------------------End of Test---------------------------");
}

//6. Extract the title of all records whose user_id = 5456

    @Test
    public void test006()
    {
        List<?>titleAllRecord = response.extract().path("findAll{it.user_id==2272669}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the title : "+titleAllRecord);
        System.out.println("------------------End of Test---------------------------");
    }

//7. Extract the body of all records whose id = 2671

    @Test
    public void test007()
    {
        List<?>titleAllBody = response.extract().path("findAll{it.id=39304}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the title : "+titleAllBody);
        System.out.println("------------------End of Test---------------------------");

    }

}
