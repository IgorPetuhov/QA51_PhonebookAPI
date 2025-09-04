package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.ContactDto;
import com.phonebook.utils.MyDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.*;
import static com.phonebook.endpoints.AuthApi.login;
import static com.phonebook.endpoints.ContactApi.addContact;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class AddContactTests extends TestBase {

    String token;

    @BeforeMethod
    public void preRequest() {

        String responseToken = login(auth).statusCode(200)
                .extract().path("token");

        token = responseToken;
    }

    @Test(dataProviderClass = MyDataProvider.class, dataProvider = "addContactFromCsv")
    public void addContactSuccessTest(ContactDto dto) {
        //String message =
        addContact(AUTH, token, dto)
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was added!"));
        //.extract().path("message");
        //System.out.println(message);
    }

    @Test
    public void addContactWithoutNameTest() {
        //ErrorDto errorDto =
        addContact(AUTH, token, ContactDto.builder()
                .lastName("Kan")
                .email("kan@gm.de")
                .phone("1234567890")
                .address("Berlin")
                .description("goalkeeper")
                .build())
                .assertThat().statusCode(400)
                .assertThat().body("message.name", containsString("must not be blank"));
        // .extract().response().as(ErrorDto.class);
        // System.out.println(errorDto.getError() + "***********" + errorDto.getMessage());
    }

    @Test
    public void addContactWithInvalidPhoneTest() {
        //ErrorDto errorDto =
        addContact(AUTH, token, ContactDto.builder()
                .name("Oliver")
                .lastName("Kan")
                .email("kan@gm.de")
                .phone("12345")
                .address("Berlin")
                .description("goalkeeper")
                .build())
                .assertThat().statusCode(400)
                .assertThat().body("message.phone", containsString("Phone number must contain only digits! And length min 10, max 15!"));
        //.extract().response().as(ErrorDto.class);
        //System.out.println(errorDto.getMessage());
    }

}

