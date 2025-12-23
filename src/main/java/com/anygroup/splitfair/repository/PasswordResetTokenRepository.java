package com.anygroup.splitfair.repository;

import com.anygroup.splitfair.model.PasswordResetToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, UUID> {

    Optional<PasswordResetToken>
    findTopByEmailAndOtpOrderByExpiresAtDesc(String email, String otp);

    @Transactional
    @Modifying
    void deleteByEmail(String email);
}