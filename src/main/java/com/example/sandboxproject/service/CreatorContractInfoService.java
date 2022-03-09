package com.example.sandboxproject.service;

import com.example.sandboxproject.dao.CreatorContractInfoDao;
import com.example.sandboxproject.entity.CreatorContractInfo;
import com.example.sandboxproject.entity.Profit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatorContractInfoService {

    private final CreatorContractInfoDao creatorContractInfoDao;
    private final ProfitService profitService;
    private final DateService dateService;


    public CreatorContractInfo getCreatorId(Long id) {
        return creatorContractInfoDao.findById(id);
    }

    public double settlementAmountOfCreatorSave(Long channelId, Long creatorId) {
        // 2월 달 값 데이터 출력(현재 달 기준 이전 달 데이터 값 1일~ 그 달의 마지막 일)
        List<Profit> profitList = profitService.getDateBetweenAndIdService(dateService.startDate(), dateService.endDate(), channelId);

        //Daily 별 총수익 금액 합계
        int profitAmount = profitService.getTotalProfitAmount(profitList);

        //크리에이터  (김영재) 계약퍼센테이지 값
        CreatorContractInfo creatorContractInfo = getCreatorId(creatorId);
        Double percent = getSettlementAmountPer(creatorContractInfo);

        //크리에이터 정산금액
        creatorContractInfo.setSettlementAmount(getSettlementMount(profitAmount, percent));
        creatorContractInfoDao.save(creatorContractInfo);
        return creatorContractInfo.getSettlementAmount();
    }

    public Double getSettlementAmountPer(CreatorContractInfo creatorContractInfo) {
        return creatorContractInfo.getSettlementAmountPer();
    }

    public Double getSettlementMount(int profitAmount, Double percent) {
        return profitAmount * percent;
    }

    public Double getContractPer(CreatorContractInfo creatorContractInfo) {
        return 1 - creatorContractInfo.getSettlementAmountPer();
    }
}
