package com.plunex.emilokan.modules.user.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String firstName;

    private String lastName;

    private String userName;

    private String phoneNumber;

    private String email;

    private String password;

}
