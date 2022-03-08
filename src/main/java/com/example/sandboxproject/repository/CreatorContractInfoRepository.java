package com.example.sandboxproject.repository;

import com.example.sandboxproject.Entity.CreatorContractInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CreatorContractInfoRepository extends JpaRepository<CreatorContractInfo,Long> {
    Optional<CreatorContractInfo> findById(Long id);
}
