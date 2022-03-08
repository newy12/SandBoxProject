package com.example.sandboxproject.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //요율퍼센트
    private Double ratePer;

    //채널(oneToMany)
    @OneToMany(mappedBy = "company")
    private List<Channel> channelList = new ArrayList<>();

    //회사명
    private String companyTitle;

    //총매출액
    private Double totalAmount;

    //순매출액
    private Double netAmount;

}
