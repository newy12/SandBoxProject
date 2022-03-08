package com.example.sandboxproject.service;

import com.example.sandboxproject.dao.ProfitDao;
import com.example.sandboxproject.entity.Profit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfitService {

    private final ProfitDao profitDao;

    public List<Profit> getDateBetweenAndIdService(Date startDate, Date endDate, Long id) {
        return profitDao.findAllByDateBetweenAndChannelId(startDate, endDate, id);
    }

    public List<Profit> getDateBetweenService(Date startDate, Date endDate) {
        return profitDao.findAllByDateBetween(startDate, endDate);
    }
    public int getTotalProfitAmount(List<Profit> profitList){
        //수익금액 초기화
        int profitAmount = 0;

        //Daily 별 총수익 금액 합계
        for (int i = 0; i < profitList.size(); i++) {
            profitAmount += profitList.get(i).getProfitAmount();
        }
        return profitAmount;
    }

    public Double getTotalProfitAmountDoubleType(List<Profit> profitList) {

        //수익금액 초기화
        Double profitAmount = 0.0;

        //Daily 별 총수익 금액 합계
        for (int i = 0; i < profitList.size(); i++) {
            profitAmount += profitList.get(i).getProfitAmount();
        }
        return profitAmount;
    }
}
