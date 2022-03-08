package com.example.sandboxproject.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
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
}