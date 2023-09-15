package com.example.sandboxproject.entity;

import com.example.sandboxproject.dto.SettlementAmountResDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    //정산금액
    private Double settlementAmount;

    //채널명
    private String channelTitle;

    //크리에이터계약정보(oneToMany)
    @OneToMany(mappedBy = "channel")
    @JsonIgnore
    private List<CreatorContractInfo> creatorContractInfoList = new ArrayList<>();


    //수익(oneToMany)
    @OneToMany(mappedBy = "channel")
    @JsonIgnore
    private List<Profit> profitList = new ArrayList<>();

    //회사(ManyToOne)
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public void setSettlementAmount(SettlementAmountResDto settlementAmountResDto){
        this.settlementAmount = settlementAmountResDto.getSettlementAmount();
    }
}
