package com.plunex.emilokan.modules.user.dto;


//public record UserResponse(String firstName, String lastName, String userName, String phoneNumber, String email) {
//
//}

import com.plunex.emilokan.modules.role.dto.UserRoleResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {

    public List<UserRoleResponse> roles = new ArrayList<>();
    public UUID id;
    public String firstName;
    public String userName;
    public String lastName;
    public String email;
}
