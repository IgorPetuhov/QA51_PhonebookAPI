package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.AuthResponseDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static com.phonebook.endpoints.AuthApi.login;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class LoginTests extends TestBase {


    @Test
    public void loginSuccessTest() {

        AuthResponseDto dto = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then().statusCode(200)
                .extract().response().as(AuthResponseDto.class);

        System.out.println(dto.getToken());
    }

    @Test
    public void loginSuccessTest2() {
        String token = login(auth)
                .assertThat().statusCode(200)
                .extract().path("token");

        System.out.println(token);
    }



    @Test
    public void loginWithWrongEmail() {
        ErrorDto errorDto = given()
                .contentType(ContentType.JSON)
                .body(AuthRequestDto.builder()
                        .username("sieg@gmx.de")
                        .password("De$123456")
                        .build())
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDto.class);
        Assert.assertEquals(errorDto.getMessage(), "Login or Password incorrect");
        //System.out.println(errorDto.getError() + "********" + errorDto.getMessage());
    }

    @Test
    public void loginWithWrongEmail2() {
        given()
                .contentType(ContentType.JSON)
                .body(AuthRequestDto.builder()
                        .username("sieg@gmx.de")
                        .password("De$123456")
                        .build())
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("error", equalTo("Unauthorized"));

    }
}
