package com.plunex.emilokan.modules.role;

import com.plunex.emilokan.exception.CustomException;
import com.plunex.emilokan.modules.role.dto.PaginationResponse;
import com.plunex.emilokan.modules.role.dto.RoleRequest;
import com.plunex.emilokan.modules.role.dto.RoleResponse;
import com.plunex.emilokan.utill.PaginationResponseMapperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    private final PaginationResponseMapperService<Role, RoleResponse> paginationResponseMapperService;


    @Autowired
    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper, PaginationResponseMapperService<Role, RoleResponse> paginationResponseMapperService) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.paginationResponseMapperService = paginationResponseMapperService;
    }

    @Override
    public List<Role> adminGetAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public PaginationResponse<RoleResponse> getRolesPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Role> result = roleRepository.findAll(pageable);
        List<RoleResponse> list = new ArrayList<>();
        for (Role item : result.getContent()) {
            list.add(modelMapper.map(item, RoleResponse.class));
        }
        return paginationResponseMapperService.convertToPaginationResponse(result, list);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleResponse save(RoleRequest request) {
        Role result = modelMapper.map(request, Role.class);
        result = roleRepository.save(result);
        return modelMapper.map(result, RoleResponse.class);
    }

    // Set other fields from request
    @Override
    public RoleResponse update(RoleRequest request) {
        Role initialResult = roleRepository.findById(UUID.fromString(request.getRoleId())).orElseThrow(
                () -> new CustomException("Resource Not Found", HttpStatus.NOT_FOUND));
        initialResult.setName(request.getName());
        Role savedResult = roleRepository.save(initialResult);
        return modelMapper.map(savedResult, RoleResponse.class);
    }

    @Override
    public RoleResponse deleteRoleById(String roleId) {
        Role result = roleRepository.findById(UUID.fromString(roleId)).orElseThrow(
                () -> new CustomException("Resource Not Found", HttpStatus.NOT_FOUND));
        roleRepository.delete(result);
        return modelMapper.map(result, RoleResponse.class);
    }

}
