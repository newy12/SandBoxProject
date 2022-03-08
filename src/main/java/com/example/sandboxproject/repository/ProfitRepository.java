package com.example.sandboxproject.repository;

import com.example.sandboxproject.Entity.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface ProfitRepository extends JpaRepository<Profit,Long> {
    List<Profit> findAllByDateBetweenAndChannelId(Date start , Date end , Long channelId);
    List<Profit> findAllByDateBetween(Date start , Date end);

}
