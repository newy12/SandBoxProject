package com.example.sandboxproject.service;

import com.example.sandboxproject.Entity.Company;
import com.example.sandboxproject.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company findById(Long id) {
        return companyRepository.findById(id).get();
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
