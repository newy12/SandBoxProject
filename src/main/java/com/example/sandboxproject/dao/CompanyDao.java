package com.example.sandboxproject.dao;

import com.example.sandboxproject.entity.Company;
import com.example.sandboxproject.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CompanyDao {

    private final CompanyRepository companyRepository;

    public Company findById(Long id) {
        return companyRepository.findById(id).get();
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
