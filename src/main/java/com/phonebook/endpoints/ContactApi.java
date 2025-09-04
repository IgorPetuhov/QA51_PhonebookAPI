package com.phonebook.endpoints;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ContactApi {

    public static ValidatableResponse getContacts(String auth, String accessToken) {
        return given()
                .header(auth, accessToken)
                .when()
                .get("contacts")
                .then()
                .assertThat();
    }

    public static ValidatableResponse addContact(String auth, String accessToken, ContactDto contactData) {
        return given()
                .header(auth, accessToken)
                .contentType(ContentType.JSON)
                .body(contactData)
                .when()
                .post("contacts")
                .then();
    }

    public static ValidatableResponse deleteContact(String auth, String accessToken, String contactId) {
        return given()
                .header(auth, accessToken)
                .when()
                .delete("contacts/" + contactId)
                .then();
    }

    public static ValidatableResponse deleteAllContacts(String auth, String accessToken) {
        return given()
                .header(auth, accessToken)
                .when()
                .delete("contacts/clear")
                .then();
    }
}
