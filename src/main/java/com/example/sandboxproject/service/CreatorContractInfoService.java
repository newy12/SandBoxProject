package com.example.sandboxproject.service;

import com.example.sandboxproject.dao.CreatorContractInfoDao;
import com.example.sandboxproject.entity.CreatorContractInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatorContractInfoService {

    private final CreatorContractInfoDao creatorContractInfoDao;

    public CreatorContractInfo getCreatorId(Long id) {
        return creatorContractInfoDao.findById(id);
    }

    public CreatorContractInfo save(CreatorContractInfo creatorContractInfo) {
        return creatorContractInfoDao.save(creatorContractInfo);
    }
    public Double getSettlementAmountPer(CreatorContractInfo creatorContractInfo){
        return creatorContractInfo.getSettlementAmountPer();
    }
    public Double getSettlementMount(int profitAmount, Double percent){
        return profitAmount * percent;
    }
    public Double getContractPer(CreatorContractInfo creatorContractInfo){
        return 1-creatorContractInfo.getSettlementAmountPer();
    }
}
