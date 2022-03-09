package com.example.sandboxproject.service;

import com.example.sandboxproject.dao.CompanyDao;
import com.example.sandboxproject.entity.Company;
import com.example.sandboxproject.entity.Profit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyDao companyDao;
    private final ProfitService profitService;
    private final DateService dateService;

    public Company getCompanyId(Long id) {
        return companyDao.findById(id);
    }

    public Double totalAmountSave(Long companyId) {
        // 2월 달 값 모든 데이터 출력
        List<Profit> profitList = profitService.getDateBetweenService(dateService.startDate(), dateService.endDate());

        //Type이 Double인 총수익 금액 출력
        Double profitAmount = profitService.getTotalProfitAmountDoubleType(profitList);

        //회사 데이터 추출
        Company company = getCompanyId(companyId);
        company.setTotalAmount(profitAmount);

        companyDao.save(company);

        return profitAmount;

    }

    public Double netAmountSave(Long companyId) {
        // 2월 달 값 모든 데이터 출력 (현재 월 기준 이전 달)
        List<Profit> profitList = profitService.getDateBetweenService(dateService.startDate(), dateService.endDate());

        //Daily 별 총수익 금액 합계
        int profitAmount = profitService.getTotalProfitAmount(profitList);
        //회사 정보 추출
        Company company = getCompanyId(companyId);
        //회사 순매출
        Double netAmount = getNetAmount(profitAmount,company);
        company.setNetAmount(netAmount);
        companyDao.save(company);
        return netAmount;
    }
    public Double getNetAmount(int profitAmount, Company company) {
        //수익금액 * 회사요율퍼센트
        return profitAmount * company.getRatePer();
    }
}
