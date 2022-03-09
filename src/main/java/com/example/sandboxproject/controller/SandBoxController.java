package com.example.sandboxproject.controller;

import com.example.sandboxproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/sandbox/exam")
@RestController
@RequiredArgsConstructor
public class SandBoxController {

    private final ChannelService channelService;
    private final CompanyService companyService;
    private final CreatorContractInfoService creatorContractInfoService;

    //특정 채널 수익금액과 계약정보에 따른 크리에이터 정산금액 조회
    @GetMapping("/settlementAmountOfCreator/{channelId}/{creatorId}")
    public Double getSettlementAmountOfCreator(@PathVariable("channelId")Long channelId,
                                               @PathVariable("creatorId")Long creatorId) {

        return creatorContractInfoService.settlementAmountOfCreatorSave(channelId,creatorId);
    }

    //크리에이터 기준으로 채널별 정산금액 조회
    @GetMapping("/settlementAmountOfChannel/{channelId}/{creatorId}")
    public Double getSettlementAmountOfChannel(@PathVariable("channelId")Long channelId,
                                               @PathVariable("creatorId")Long creatorId) {

        return channelService.settlementAmountOfChannelSave(channelId,creatorId);
    }

    //월별 회사 총매출 조회
    @GetMapping("/companyTotalAmount/{companyId}")
    public Double getCompanyTotalAmount(@PathVariable("companyId")Long companyId) {
        return companyService.totalAmountSave(companyId);
    }


    //월별 회사 순매출 조회
    @GetMapping("/companyNetAmount/{companyId}")
    public Double getCompanyNetAmount(@PathVariable("companyId")Long companyId) {

        return companyService.netAmountSave(companyId);
    }

}




