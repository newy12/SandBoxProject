package com.example.sandboxproject.service;

import com.example.sandboxproject.dao.CompanyDao;
import com.example.sandboxproject.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyDao companyDao;

    public Company getCompanyId(Long id) {
        return companyDao.findById(id);
    }

    public Company save(Company company) {
        return companyDao.save(company);
    }

    public Double getNetAmount(int profitAmount, Company company) {
        return profitAmount * company.getRatePer();
    }
}
