package com.phonebook.utils;

import com.phonebook.dto.ContactDto;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {

    @DataProvider
    public Iterator<Object[]> addContactFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader
                (new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        String[] split = line.split(",");
        while (line != null) {
            list.add(new Object[]{ContactDto.builder()
                    .name(split[0])
                    .lastName(split[1])
                    .email(split[2])
                    .phone(split[3])
                    .address(split[4])
                    .description(split[5])
                    .build()
            });
            line = reader.readLine();
        }
        return list.iterator();
    }
}
