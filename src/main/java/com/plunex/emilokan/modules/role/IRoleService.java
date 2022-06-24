package com.plunex.emilokan.modules.role;


import com.plunex.emilokan.modules.role.dto.PaginationResponse;
import com.plunex.emilokan.modules.role.dto.RoleRequest;
import com.plunex.emilokan.modules.role.dto.RoleResponse;

import java.util.List;

public interface IRoleService {

    // CRUD
    List<Role> adminGetAllRoles();

    PaginationResponse<RoleResponse> getRolesPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    Role save(Role role);

    RoleResponse save(RoleRequest request);

    RoleResponse update(RoleRequest request);

    RoleResponse deleteRoleById(String roleId);

}
