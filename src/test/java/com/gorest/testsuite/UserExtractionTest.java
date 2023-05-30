package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest
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
                .get("/users/?page=1&per_page=20")
                .then().statusCode(200);

    }

//    1. Extract the All Ids

    @Test
    public void test001()
    {
        List<Integer> id = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Ids : "+id);
        System.out.println("------------------End of Test---------------------------");
    }


//2. Extract the all Names
@Test
public void test002()
{
    List<String> name = response.extract().path("name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All Names : "+name);
    System.out.println("------------------End of Test---------------------------");
}

//3. Extract the name of 5th object
@Test
public void test003()
{
    String name = response.extract().path("[4].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The name of 5th object : "+name);
    System.out.println("------------------End of Test---------------------------");
}

//4. Extract the names of all object whose status = inactive
@Test
public void test004()
{
    List<String>  inActive = response.extract().path("findAll{it.status=='inactive'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Name of inactivet : "+inActive);
    System.out.println("------------------End of Test---------------------------");
}

//5. Extract the gender of all the object whose status = active
    @Test
    public void test005()
    {
        List<?> listActive = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of active : "+listActive);
        System.out.println("------------------End of Test---------------------------");

    }

//6. Print the names of the object whose gender = female
@Test
public void test006()
{
    List<?> listAll = response.extract().path("findAll{it.gender=='female'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All Name of femals : "+listAll);
    System.out.println("------------------End of Test---------------------------");
}

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007()
    {
        List<String>  inActiveEmail = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the emails of the object where status = inactive : "+inActiveEmail);
        System.out.println("------------------End of Test---------------------------");

    }

//8. Get the ids of the object where gender = male
@Test
public void test008()
{
    List<String>  id = response.extract().path("findAll{it.gender='male'}.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All the ids of the object where gender = male : "+id);
    System.out.println("------------------End of Test---------------------------");

}

//9. Get all the status
@Test
public void test009()
{
    List<String> status = response.extract().path("status.grep()");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All the status : "+status);
    System.out.println("------------------End of Test---------------------------");
}

//10. Get email of the object where name = Karthik Dubashi IV
@Test
public void test010()
{
    List<?> listAllEmail = response.extract().path("findAll{it.name=='Gudakesa Guha'}.email");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All email of the object where name = Karthik Dubashi IV : "+listAllEmail);
    System.out.println("------------------End of Test---------------------------");
}

//11. Get gender of id = 5471

    @Test
    public void test011()
    {
        String genderId = response.extract().path("[1].gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get gender of id = 2272683 : "+genderId);
        System.out.println("------------------End of Test---------------------------");
    }


}
