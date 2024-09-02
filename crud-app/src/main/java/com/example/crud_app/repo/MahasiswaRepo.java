package com.example.crud_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crud_app.model.Mahasiswa;

public interface MahasiswaRepo extends JpaRepository<Mahasiswa, Long> {

}
