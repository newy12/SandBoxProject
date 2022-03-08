package com.example.sandboxproject.controller;

import com.example.sandboxproject.entity.Channel;
import com.example.sandboxproject.entity.Company;
import com.example.sandboxproject.entity.CreatorContractInfo;
import com.example.sandboxproject.entity.Profit;
import com.example.sandboxproject.service.ChannelService;
import com.example.sandboxproject.service.CompanyService;
import com.example.sandboxproject.service.CreatorContractInfoService;
import com.example.sandboxproject.service.ProfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ChannelService channelService;
    private final CompanyService companyService;
    private final CreatorContractInfoService creatorContractInfoService;
    private final ProfitService profitService;

    //달의 첫일
    public Date startDate() {
        //현재 3월기준 2월달의 날짜 구하기 (현재 월 기준 이전 달 1일 ~ 마지막 일)
        Calendar startDate = Calendar.getInstance();
        //항상 이전 달 세팅
        startDate.add(Calendar.MONTH, -1);
        //항상 1일 세팅
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        //분 초 밀리세컨드 = 0으로 초기화
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        return startDate.getTime();
    }

    //달의 마지막일
    public Date endDate() {
        Calendar endDate = Calendar.getInstance();
        //달의 마지막일 구하기
        int dayOfMonth = endDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        //항상 이전 달 세팅
        endDate.add(Calendar.MONTH, -1);
        //분 초 밀리세컨드 = 0으로 초기화
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);
        return endDate.getTime();
    }

    //특정 채널 수익금액과 계약정보에 따른 크리에이터 정산금액 조회
    @GetMapping("/settlementAmountOfCreator")
    public Double settlementAmountOfCreator() {

        // 2월 달 값 데이터 출력(현재 달 기준 이전 달 데이터 값 1일~ 그 달의 마지막 일)
        List<Profit> profitList = profitService.findAllByDateBetweenAndChannelId(startDate(), endDate(), 1L);

        //수익금액 초기화
        int profitAmount = 0;

        //Daily 별 총수익 금액 합계
        for (int i = 0; i < profitList.size(); i++) {
            profitAmount += profitList.get(i).getProfitAmount();
        }
        //크리에이터  (김영재) 계약퍼센테이지 값
        CreatorContractInfo creatorContractInfo = creatorContractInfoService.findById(1L);
        Double percent = creatorContractInfo.getSettlementAmountPer();

        //크리에이터 정산금액
        creatorContractInfo.setSettlementAmount((profitAmount * percent));
        creatorContractInfoService.save(creatorContractInfo);
        return creatorContractInfo.getSettlementAmount();
    }

    //크리에이터 기준으로 채널별 정산금액 조회
    @GetMapping("/settlementAmountOfChannel")
    public Double settlementAmountOfChannel() {
        // 2월 달 값 데이터 출력
        List<Profit> profitList = profitService.findAllByDateBetweenAndChannelId(startDate(), endDate(), 1L);

        //수익금액 초기화
        int profitAmount = 0;

        //Daily 별 총수익 금액 합계
        for (int i = 0; i < profitList.size(); i++) {
            profitAmount += profitList.get(i).getProfitAmount();
        }
        //크리에이터  (김영재) 계약퍼센테이지 값
        CreatorContractInfo creatorContractInfo = creatorContractInfoService.findById(1L);

        //채널 계약퍼센테이지 값 = 1 - 크리에이터(김영재)계약퍼센테이지
        Double percent = 1 - creatorContractInfo.getSettlementAmountPer();

        Channel channel = channelService.findById(creatorContractInfo.getChannel().getId());
        channel.setSettlementAmount(profitAmount * percent);
        channelService.save(channel);

        return profitAmount * percent;
    }

    //월별 회사 총매출 조회
    @GetMapping("/companyTotalAmount")
    public Double companyTotalAmount() {

        // 2월 달 값 모든 데이터 출력
        List<Profit> profitList = profitService.findAllByDateBetween(startDate(), endDate());

        //수익금액 초기화
        Double profitAmount = 0.0;

        //Daily 별 총수익 금액 합계
        for (int i = 0; i < profitList.size(); i++) {
            profitAmount += profitList.get(i).getProfitAmount();
        }
        //회사 데이터 추출
        Company company = companyService.findById(1L);

        company.setTotalAmount(profitAmount);
        companyService.save(company);

        return profitAmount;
    }


    //월별 회사 순매출 조회
    @GetMapping("/companyNetAmount")
    public Double companyNetAmount() {
        // 2월 달 값 모든 데이터 출력 (현재 월 기준 이전 달)
        List<Profit> profitList = profitService.findAllByDateBetween(startDate(), endDate());

        //수익금액 초기화
        int profitAmount = 0;

        //Daily 별 총수익 금액 합계
        for (int i = 0; i < profitList.size(); i++) {
            profitAmount += profitList.get(i).getProfitAmount();
        }
        //회사 순매출
        Company company = companyService.findById(1L);

        Double netAmount = profitAmount * company.getRatePer();

        company.setNetAmount(netAmount);
        companyService.save(company);

        return netAmount;
    }

}




