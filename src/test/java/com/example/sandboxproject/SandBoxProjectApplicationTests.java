package com.example.sandboxproject;

import com.example.sandboxproject.entity.Channel;
import com.example.sandboxproject.entity.Company;
import com.example.sandboxproject.entity.CreatorContractInfo;
import com.example.sandboxproject.entity.Profit;
import com.example.sandboxproject.repository.ChannelRepository;
import com.example.sandboxproject.repository.CompanyRepository;
import com.example.sandboxproject.repository.CreatorContractInfoRepository;
import com.example.sandboxproject.repository.ProfitRepository;
import com.example.sandboxproject.service.ChannelService;
import com.example.sandboxproject.service.CompanyService;
import com.example.sandboxproject.service.DateService;
import com.example.sandboxproject.service.ProfitService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SandBoxProjectApplicationTests {

    @Autowired
    private ProfitRepository profitRepository;
    @Autowired
    private CreatorContractInfoRepository creatorContractInfoRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProfitService profitService;
    @Autowired
    private DateService dateService;

    @Test
    @DisplayName("현재 달 기준 이전달의 첫날 출력")
    public void 이전달의첫날(){

        //given
        TimeZone utc = TimeZone.getTimeZone("UTC");
        Calendar startDate = Calendar.getInstance(utc);
        startDate.add(Calendar.MONTH,-1);
        startDate.set(Calendar.DAY_OF_MONTH,1);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.SECOND,0);
        startDate.set(Calendar.MILLISECOND,0);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(utc);
        format.format(startDate.getTime());
        String theDay = format.format(startDate.getTime());
        //then
        assertThat(theDay).isEqualTo("2022-02-01");
    }
    @Test
    @DisplayName("현재 달 기준 이전달의 마지막날 출력")
    public void 이전달의마지막날(){
        //given
        TimeZone utc = TimeZone.getTimeZone("UTC");
        Calendar endDate = Calendar.getInstance(utc);
        int dayOfMonth = endDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        endDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        endDate.add(Calendar.MONTH,-1);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.SECOND,0);
        endDate.set(Calendar.MILLISECOND,0);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(utc);
        format.format(endDate.getTime());
        String theDay = format.format(endDate.getTime());
        //then
        assertThat(theDay).isEqualTo("2022-02-28");
    }


    @Test
    @DisplayName("이전달1일~이전달마지막일 사이의 있는 데이터 값(갯수) 제대로 가져오는지")
    public void 이전달첫날과이전달마지막사이값(){
        //given
        //현재 3월기준 2월달의 날짜 구하기 (현재 월 기준 이전 달 1일 ~ 마지막 일)
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        Calendar endDate = Calendar.getInstance();
        int dayOfMonth = endDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        endDate.add(Calendar.MONTH, -1);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);
        //2월1일~2월28일 사이값이라고 가정했을때 28개
        int list = 28;
        //when
        List<Profit> profitList = profitRepository.findAllByDateBetweenAndChannelId(startDate.getTime(),endDate.getTime(),1L);
        //then
        assertThat(list).isEqualTo(profitList.size());

    }
    @Test
    @DisplayName("올바른 퍼센테이지 값 확인")
    public void 퍼센테이지값확인(){
        //크리에이터  (김영재) 계약퍼센테이지 값
        CreatorContractInfo creatorContractInfo = creatorContractInfoRepository.findById(1L).get();

        Double value = 0.5;
        //채널 계약퍼센테이지 값 = 1 - 크리에이터(김영재)계약퍼센테이지
        Double percent = 1 - creatorContractInfo.getSettlementAmountPer();
        assertThat(value).isEqualTo(percent);
    }
    @Test
    @DisplayName("회사정보 제대로가져오는 지")
    public void 회사정보확인(){
        //given
        String companyTitle = "샌드박스";
        //when
        Company company = companyRepository.findById(1L).get();
        //then
        assertThat(company.getCompanyTitle()).isEqualTo(companyTitle);
    }
    @Test
    @DisplayName("크리에이터 정보 제대로 가져오는 지")
    public void 크리에이터정보확인(){
        //given
        String creatorName = "김영재";
        //when
        CreatorContractInfo creatorContractInfo = creatorContractInfoRepository.findById(1L).get();
        //then
        assertThat(creatorContractInfo.getCreatorName()).isEqualTo(creatorName);
    }
    @Test
    @DisplayName("채널 정보 제대로 가져오는 지")
    public void 채널정보확인(){
        //given
        String channelTitle = "영재채널TV";
        //when
        Channel channel = channelRepository.findById(1L).get();
        //then
        assertThat(channel.getChannelTitle()).isEqualTo(channelTitle);
    }
    @Test
    @DisplayName("채널기준정산금액 제대로 가져오는 지")
    public void 채널기준정산금액확인(){
        //given
        Double result = 15.0;
        //then
        assertThat(result).isEqualTo(channelService.getSettlementMount(30,0.5));
    }
    @Test
    @DisplayName("월별회사의순매출금액 제대로 가져오는 지")
    public void 회사의순매출확인(){
        //given
        Double result = 15.0;
        //when
        Company company = companyService.getCompanyId(1L);
        //then
        //company Id =1 에는 0.5의요율을 가지고있다.
        assertThat(result).isEqualTo(companyService.getNetAmount(30,company));
    }
    @Test
    @DisplayName("월별회사의총매출금액 제대로 가져오는 지")
    public void 회사의총매출확인(){
        //given
        int totalAmount = 13000000;
        //when
        List<Profit> profitList = profitRepository.findAllByDateBetweenAndChannelId(dateService.startDate(),dateService.endDate(),1L);
        //then
        assertThat(totalAmount).isEqualTo(profitService.getTotalProfitAmount(profitList));
    }


}
