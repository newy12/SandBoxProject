package com.example.sandboxproject.dao;

import com.example.sandboxproject.entity.CreatorContractInfo;
import com.example.sandboxproject.repository.CreatorContractInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CreatorContractInfoDao {

    private final CreatorContractInfoRepository creatorContractInfoRepository;

    public CreatorContractInfo findById(Long id) {
        return creatorContractInfoRepository.findById(id).get();
    }

    public CreatorContractInfo save(CreatorContractInfo creatorContractInfo) {
        return creatorContractInfoRepository.save(creatorContractInfo);
    }
}
