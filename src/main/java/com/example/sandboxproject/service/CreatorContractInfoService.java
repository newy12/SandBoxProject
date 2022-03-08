package com.example.sandboxproject.service;

import com.example.sandboxproject.Entity.CreatorContractInfo;
import com.example.sandboxproject.repository.CreatorContractInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatorContractInfoService {

    private final CreatorContractInfoRepository creatorContractInfoRepository;

    public CreatorContractInfo findById(Long id) {
        return creatorContractInfoRepository.findById(id).get();
    }

    public CreatorContractInfo save(CreatorContractInfo creatorContractInfo) {
        return creatorContractInfoRepository.save(creatorContractInfo);
    }
}
