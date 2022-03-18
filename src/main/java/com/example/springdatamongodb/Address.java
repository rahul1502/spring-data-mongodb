package com.example.springdatamongodb;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Annotation from Lombok that will automatically define Constructor
public class Address {
    private String country;
    private String city;
    private String postCode;
}
