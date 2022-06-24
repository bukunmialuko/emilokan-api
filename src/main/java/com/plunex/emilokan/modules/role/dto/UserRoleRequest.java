package com.plunex.emilokan.modules.role.dto;

import lombok.Builder;

@Builder
public class UserRoleRequest {
    public String userId;
    public String roleId;
}
