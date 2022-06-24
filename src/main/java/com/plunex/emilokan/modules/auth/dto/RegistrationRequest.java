package com.plunex.emilokan.modules.auth.dto;

import lombok.Data;

@Data
public class RegistrationRequest {

    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public String phoneNumber;

}
