package com.phonebook.tests;

import com.phonebook.core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static com.phonebook.endpoints.AuthApi.login;
import static com.phonebook.endpoints.ContactApi.deleteAllContacts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteAllContactTests extends TestBase {

    String token;

    @BeforeMethod
    public void preRequest() {
        String responseToken = login(auth).statusCode(200)
                .extract().path("token");
        token = responseToken;
    }

    @Test
    public void deleteAddContactsSuccessTest() {
        deleteAllContacts(AUTH, token)
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("contacts was deleted"));
    }
}
