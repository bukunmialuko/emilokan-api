package com.plunex.emilokan.utill;

import com.plunex.emilokan.modules.role.dto.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationResponseMapperService<I, O> {

    public PaginationResponse<O> convertToPaginationResponse(Page<I> inputPage, List<O> list) {
        PaginationResponse<O> response = new PaginationResponse<O>();
        response.setCurrentPage(inputPage.getNumber());
        response.setTotalPages(inputPage.getTotalPages());
        response.setTotalItems(inputPage.getTotalElements());
        response.setSortDir(inputPage.getSort().toString());
        response.setReverseSortDir(inputPage.getSort().toString().equalsIgnoreCase(Sort.Direction.ASC.name()) ? "DESC" : "ASC");
        response.setData(list);
        return response;
    }

}
