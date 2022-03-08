package com.example.sandboxproject.service;

import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;

@Service
public class DateService {

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
}
