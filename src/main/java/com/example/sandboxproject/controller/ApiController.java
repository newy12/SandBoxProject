package com.example.sandboxproject.controller;

import com.example.sandboxproject.entity.Channel;
import com.example.sandboxproject.entity.Company;
import com.example.sandboxproject.entity.CreatorContractInfo;
import com.example.sandboxproject.entity.Profit;
import com.example.sandboxproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ChannelService channelService;
    private final CompanyService companyService;
    private final CreatorContractInfoService creatorContractInfoService;
    private final ProfitService profitService;
    private final DateService dateService;


    //특정 채널 수익금액과 계약정보에 따른 크리에이터 정산금액 조회
    @GetMapping("/settlementAmountOfCreator")
    public Double settlementAmountOfCreator() {

        // 2월 달 값 데이터 출력(현재 달 기준 이전 달 데이터 값 1일~ 그 달의 마지막 일)
        List<Profit> profitList = profitService.getDateBetweenAndIdService(dateService.startDate(), dateService.endDate(), 1L);

        //Daily 별 총수익 금액 합계
        int profitAmount = profitService.getTotalProfitAmount(profitList);

        //크리에이터  (김영재) 계약퍼센테이지 값
        CreatorContractInfo creatorContractInfo = creatorContractInfoService.getCreatorId(1L);
        Double percent = creatorContractInfoService.getSettlementAmountPer(creatorContractInfo);

        //크리에이터 정산금액
        creatorContractInfo.setSettlementAmount(creatorContractInfoService.getSettlementMount(profitAmount,percent));
        creatorContractInfoService.save(creatorContractInfo);
        return creatorContractInfo.getSettlementAmount();
    }

    //크리에이터 기준으로 채널별 정산금액 조회
    @GetMapping("/settlementAmountOfChannel")
    public Double settlementAmountOfChannel() {
        // 2월 달 값 데이터 출력
        List<Profit> profitList = profitService.getDateBetweenAndIdService(dateService.startDate(), dateService.endDate(), 1L);
        //Daily 별 총수익 금액 합계
        int profitAmount = profitService.getTotalProfitAmount(profitList);
        //크리에이터  (김영재) 계약퍼센테이지 값
        CreatorContractInfo creatorContractInfo = creatorContractInfoService.getCreatorId(1L);

        //채널 계약퍼센테이지 값 = 1 - 크리에이터(김영재)계약퍼센테이지
        Double percent = creatorContractInfoService.getContractPer(creatorContractInfo);

        Channel channel = channelService.getChannelId(creatorContractInfo.getChannel().getId());
        channel.setSettlementAmount(channelService.getSettlementMount(profitAmount,percent));
        channelService.save(channel);

        return profitAmount * percent;
    }

    //월별 회사 총매출 조회
    @GetMapping("/companyTotalAmount")
    public Double companyTotalAmount() {

        // 2월 달 값 모든 데이터 출력
        List<Profit> profitList = profitService.getDateBetweenService(dateService.startDate(), dateService.endDate());

        Double profitAmount = profitService.getTotalProfitAmountDoubleType(profitList);
        //회사 데이터 추출
        Company company = companyService.getCompanyId(1L);

        company.setTotalAmount(profitAmount);
        companyService.save(company);

        return profitAmount;
    }


    //월별 회사 순매출 조회
    @GetMapping("/companyNetAmount")
    public Double companyNetAmount() {
        // 2월 달 값 모든 데이터 출력 (현재 월 기준 이전 달)
        List<Profit> profitList = profitService.getDateBetweenService(dateService.startDate(), dateService.endDate());

        //Daily 별 총수익 금액 합계
        int profitAmount = profitService.getTotalProfitAmount(profitList);
        //회사 정보 추출
        Company company = companyService.getCompanyId(1L);
        //회사 순매출
        Double netAmount = companyService.getNetAmount(profitAmount,company);

        company.setNetAmount(netAmount);
        companyService.save(company);

        return netAmount;
    }

}




