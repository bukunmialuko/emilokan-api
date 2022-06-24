package com.plunex.emilokan.modules.role.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {

    public List<UserRoleResponse> roles = new ArrayList<>();
    public UUID id;
    public String firstName;
    public String lastName;
    public String email;
}
