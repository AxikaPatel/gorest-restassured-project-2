package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest
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

    //1. Verify the if the total record is 20
    @Test
    public void test001()
    {
        response.body("total.size()", equalTo(20));
    }

     //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002()
    {

       response.body("[0].id",equalTo(2272683));
        response.body("[0].name",equalTo("Chaitan Iyengar"));

    }
     //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003()
    {
       response.body("name.grep()",hasItem("Devdan Kocchar"));
    }

     //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. BodhanGuha, Karthik Dubashi IV)

    @Test
    public void test004()
    {
        response.body("name.grep()",hasItems("Devdan Kocchar","The Hon. Prema Guha","Abani Kaul"));
    }

    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005()
    {
        response.body("find{it.id == 2272612}.email",equalTo("chandraayan_chopra@dach.example"));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006()
    {
        response.body("find{it.name == 'Gautam Kaul'}.status",equalTo("active"));

    }


    //7.Verify the Gender = male of user name is “Niro Prajapat”

    @Test
    public void test007()
    {
        response.body("find{it.name == 'Chandak Chopra'}.gender",equalTo("male"));

    }





}
