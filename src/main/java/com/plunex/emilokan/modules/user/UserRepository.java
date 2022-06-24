package com.plunex.emilokan.modules.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserNameIgnoreCase(String userName);
    Optional<User> findByEmailIgnoreCase(String email);
    //    boolean existsByUserName(String userName);
    List<User> findAllByEmailIgnoreCase(String email);

}