package com.plunex.emilokan.modules.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query(value = "SELECT * FROM Role", nativeQuery = true)
    Page<Role> getRolesByUserId(UUID userId, Pageable pageable);
}
