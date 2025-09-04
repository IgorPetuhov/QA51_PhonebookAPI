package com.phonebook.endpoints;

import com.phonebook.dto.AuthRequestDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuthApi {

    public static ValidatableResponse login(AuthRequestDto loginData) {
        return given()
                .contentType(ContentType.JSON)
                .body(loginData)
                .when()
                .post("user/login/usernamepassword")
                .then();
    }
}
