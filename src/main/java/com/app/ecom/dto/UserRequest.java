package com.app.ecom.dto;

import com.app.ecom.model.AddressDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class UserRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    // Accept the API-friendly "address" name and the existing "addressDTO" name.
    @JsonAlias("address")
    private AddressDTO addressDTO;


}
