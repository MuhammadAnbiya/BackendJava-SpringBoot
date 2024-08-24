    package com.domain.models.repos;

    import java.util.Optional;

    import org.springframework.data.repository.PagingAndSortingRepository;

    import com.domain.models.entities.AppUser;

    public interface AppUserRepo extends PagingAndSortingRepository<AppUser, Long> {

        Optional<AppUser> findByEmail(String email);
        
    }
