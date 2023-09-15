package com.example.sandboxproject.dto;

import lombok.Data;

@Data
public class NetAmountResDto {
    private double netAmount;

    public NetAmountResDto(double netAmount){
        this.netAmount = netAmount;
    }
}
