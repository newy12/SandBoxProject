package com.example.sandboxproject.service;

import com.example.sandboxproject.dao.ChannelDao;
import com.example.sandboxproject.entity.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelService {

    private final ChannelDao channelDao;

    public Channel getChannelId(Long id) {
        return channelDao.findById(id);
    }

    public Channel save(Channel channel) {
        return channelDao.save(channel);
    }

    public Double getSettlementMount(int profitAmount, Double percent) {
        return profitAmount * percent;
    }
}
