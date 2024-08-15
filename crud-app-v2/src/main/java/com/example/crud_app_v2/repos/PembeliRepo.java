package com.example.crud_app_v2.repos;

import com.example.crud_app_v2.model.Pembeli;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PembeliRepo extends JpaRepository<Pembeli, Long> {
}
