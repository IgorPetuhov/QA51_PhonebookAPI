package com.phonebook.tests;

import com.phonebook.core.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.*;
import static com.phonebook.endpoints.AuthApi.login;
import static com.phonebook.endpoints.ContactApi.addContact;
import static com.phonebook.endpoints.ContactApi.deleteContact;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdTests extends TestBase {

    String id;
    String token;

    @BeforeMethod
    public void preRequest() {
        String responseToken = login(auth).statusCode(200)
                .extract().path("token");

        token = responseToken;

        String message = addContact(AUTH, token, contactDto)
                .assertThat().statusCode(200)
                .extract().path("message");

        String[] split = message.split(": ");
        id = split[1];
    }

    @Test
    public void deleteByIdSuccessTest() {
        //String message =
        deleteContact(AUTH, token, id)
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was deleted!"));
        //.extract().path("message");
        //System.out.println(message);
    }

    @Test
    public void deleteWithWrongIdTest() {
        // String message =
        deleteContact(AUTH, token, "2haj-sj354-3r3-3465676")
                .assertThat().statusCode(400)
                .assertThat().body("message", containsString("not found in your contacts!"));
        //.extract().path("message");
        //System.out.println(message);
    }
}
