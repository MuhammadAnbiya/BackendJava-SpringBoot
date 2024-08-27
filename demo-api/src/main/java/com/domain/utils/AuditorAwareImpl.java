package com.domain.utils;

import java.util.Optional;

import com.domain.models.entities.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Mendapatkan otentikasi dari SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Memastikan otentikasi tidak null dan principal adalah instance AppUser
        if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            return Optional.ofNullable(currentUser.getEmail());
        }

        return Optional.empty(); // Jika tidak ada otentikasi atau tidak valid
    }
}
