package com.example.sandboxproject.dto;

import lombok.Data;

@Data
public class TotalAmountResDto {
    private double totalAmount;

    public TotalAmountResDto(double totalAmount){
        this.totalAmount = totalAmount;
    }
}
