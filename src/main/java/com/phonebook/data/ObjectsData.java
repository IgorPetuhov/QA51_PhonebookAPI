package com.phonebook.data;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.ContactDto;

public class ObjectsData {

    public static AuthRequestDto auth = AuthRequestDto.builder()
            .username("gosha@dmx.de")
            .password("De$123456")
            .build();

    public static ContactDto contactDto = ContactDto.builder()
            .name("Oliver")
            .lastName("Kan")
            .email("kan@gm.de")
            .phone("1234567890")
            .address("Berlin")
            .description("goalkeeper")
            .build();
}
