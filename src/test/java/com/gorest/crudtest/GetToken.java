package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static com.gorest.utils.TestUtils.getRandomValue;
import static io.restassured.RestAssured.given;

public class GetToken extends TestBase
{
    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Kavy Patel");
        userPojo.setEmail( getRandomValue() + "@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization", "Bearer 818df315e290db146769851c80292d82fcb69ce8e3b89e4b854e5ea8b3e96446")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void userGetSuccessfully() {
        Response response = given()
                .header("Authorization", "Bearer 818df315e290db146769851c80292d82fcb69ce8e3b89e4b854e5ea8b3e96446")
                .header("Connection", "keep-alive")
                .when()

                .get("/users/");
        response.prettyPrint();
        response.then().statusCode(200);

    }


    @Test
    public void userUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();

        userPojo.setName("Kavy Patel");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization","Bearer 818df315e290db146769851c80292d82fcb69ce8e3b89e4b854e5ea8b3e96446")
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .when()
                .body(userPojo)
                .put("/users/2329056");
        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test
    public void userDeleteSuccessfully() {
        Response response = given()
                .header("Authorization", "Bearer 818df315e290db146769851c80292d82fcb69ce8e3b89e4b854e5ea8b3e96446")
                .header("Connection", "keep-alive")
                .when()
                .delete("/users/2329056");
        response.prettyPrint();
        response.then().statusCode(204);


    }


}


