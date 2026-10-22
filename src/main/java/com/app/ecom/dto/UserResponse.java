package com.app.ecom.dto;

import com.app.ecom.model.AddressDTO;
import com.app.ecom.model.userRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private userRole role;
    private AddressDTO addressDTO;

}
