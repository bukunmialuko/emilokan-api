package com.plunex.emilokan.modules.role.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    public String token;
}
