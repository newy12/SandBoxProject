package com.example.sandboxproject.dao;

import com.example.sandboxproject.entity.Profit;
import com.example.sandboxproject.repository.ProfitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProfitDao {

    private final ProfitRepository profitRepository;

    public List<Profit> findAllByDateBetweenAndChannelId(Date startDate, Date endDate, Long id) {
        return profitRepository.findAllByDateBetweenAndChannelId(startDate, endDate, id);
    }

    public List<Profit> findAllByDateBetween(Date startDate, Date endDate) {
        return profitRepository.findAllByDateBetween(startDate, endDate);
    }
}
