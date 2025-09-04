package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AllContactsDto;
import com.phonebook.dto.ContactDto;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static com.phonebook.endpoints.AuthApi.login;
import static com.phonebook.endpoints.ContactApi.getContacts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetContactsTests extends TestBase {
    String token;

    @BeforeMethod
    public void preRequest() {


        String responseToken = login(auth).statusCode(200)
                .extract().path("token");

        token = responseToken;
    }

    @Test
    public void getContactSuccessTest() {

        AllContactsDto contactsDto = getContacts(AUTH,token).statusCode(200)
                .extract().body().as(AllContactsDto.class);

        for (ContactDto contact : contactsDto.getContacts()) {
            System.out.println(contact.getId() + "************" + contact.getName());
            System.out.println("========================");
        }
    }



    @Test
    public void getContactsWithWrongTokenTest() {

        getContacts(AUTH,"234jkhgddslkgj").statusCode(401)
                .assertThat().body("error", equalTo("Unauthorized"));
    }
}
