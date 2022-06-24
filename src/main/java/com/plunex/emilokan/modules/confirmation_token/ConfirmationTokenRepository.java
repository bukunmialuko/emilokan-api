package com.plunex.emilokan.modules.confirmation_token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, UUID> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
