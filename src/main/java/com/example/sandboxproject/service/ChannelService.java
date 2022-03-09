package com.example.sandboxproject.service;

import com.example.sandboxproject.dao.ChannelDao;
import com.example.sandboxproject.entity.Channel;
import com.example.sandboxproject.entity.CreatorContractInfo;
import com.example.sandboxproject.entity.Profit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChannelService {

    private final ChannelDao channelDao;
    private final ProfitService profitService;
    private final DateService dateService;
    private final CreatorContractInfoService creatorContractInfoService;

    public Channel getChannelId(Long id) {
        return channelDao.findById(id);
    }

    public double settlementAmountOfChannelSave(Long channelId , Long creatorId) {
        // 2월 달 값 데이터 출력
        List<Profit> profitList = profitService.getDateBetweenAndIdService(dateService.startDate(), dateService.endDate(), channelId);
        //Daily 별 총수익 금액 합계
        int profitAmount = profitService.getTotalProfitAmount(profitList);
        //크리에이터  (김영재) 계약퍼센테이지 값
        CreatorContractInfo creatorContractInfo = creatorContractInfoService.getCreatorId(creatorId);

        //채널 계약퍼센테이지 값 = 1 - 크리에이터(김영재)계약퍼센테이지
        Double percent = creatorContractInfoService.getContractPer(creatorContractInfo);

        //채널 정산금액
        Channel channel = getChannelId(creatorContractInfo.getChannel().getId());
        channel.setSettlementAmount(getSettlementMount(profitAmount,percent));
        channelDao.save(channel);
        return profitAmount * percent;
    }


    public Double getSettlementMount(int profitAmount, Double percent) {
        //수익금액 * 퍼센트
        return profitAmount * percent;
    }
}
