package com.example.sandboxproject.repository;

import com.example.sandboxproject.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
