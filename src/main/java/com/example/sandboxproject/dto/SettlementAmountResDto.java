package com.example.sandboxproject.dto;

import lombok.Data;

@Data
public class SettlementAmountResDto {
    private double settlementAmount;

    public SettlementAmountResDto(double settlementAmount){
        this.settlementAmount = settlementAmount;
    }
}
