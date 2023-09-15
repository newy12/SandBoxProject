package com.example.sandboxproject.entity;

import com.example.sandboxproject.dto.SettlementAmountResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatorContractInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //크리에이터 이름
    private String creatorName;

    //채널(ManyToOne)
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    //정산금액
    private Double settlementAmount;

    //정산금액 요율
    private double settlementAmountPer;


    public void setSettlementAmount(SettlementAmountResDto settlementAmountResDto){
        this.settlementAmount = settlementAmountResDto.getSettlementAmount();
    }
}