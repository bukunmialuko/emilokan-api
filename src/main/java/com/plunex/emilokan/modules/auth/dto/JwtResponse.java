package com.plunex.emilokan.modules.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    public String token;
}
