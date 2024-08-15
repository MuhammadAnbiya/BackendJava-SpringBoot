package com.example.crud_app_v2.repos;

import com.example.crud_app_v2.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {
}
