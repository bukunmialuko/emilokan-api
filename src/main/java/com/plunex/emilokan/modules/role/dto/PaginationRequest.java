package com.plunex.emilokan.modules.role.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {
    public int pageNo;
    public String sortField;
    public String sortDirection;
    public int pageSize;
}
