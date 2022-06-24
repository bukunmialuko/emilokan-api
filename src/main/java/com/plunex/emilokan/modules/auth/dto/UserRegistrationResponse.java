package com.plunex.emilokan.modules.auth.dto;

import lombok.Data;

@Data
public class UserRegistrationResponse {
    public String firstName;
    public String lastName;
    public String email;
}
