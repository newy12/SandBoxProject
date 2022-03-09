package com.example.sandboxproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Profit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //수익금액
    private Double profitAmount;

    //채널(ManyToOne)
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    //날짜기준
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
