// package com.domain.utils;

// import java.util.Optional;

// import com.domain.models.entities.AppUser;

// import org.springframework.data.domain.AuditorAware;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;


//     public class AuditorAwareImpl implements AuditorAware<String> { 

//         @SuppressWarnings("null")
//         @Override
//         public Optional<String> getCurrentAuditor() {  
//             Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

//             if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
//                 AppUser currentUser = (AppUser) authentication.getPrincipal();
//                 return Optional.ofNullable(currentUser.getEmail());
//             }

//             return Optional.empty(); // Jika tidak ada otentikasi atau tidak valid
//         }
// }
