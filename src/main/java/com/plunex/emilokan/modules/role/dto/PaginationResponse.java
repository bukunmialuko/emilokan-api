package com.plunex.emilokan.modules.role.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {

    private int currentPage;
    private int totalPages;
    private long totalItems;
    private String sortField;
    private String sortDir;
    private String reverseSortDir;
    private List<T> data;

}
