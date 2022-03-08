package com.example.sandboxproject.dao;

import com.example.sandboxproject.entity.Channel;
import com.example.sandboxproject.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChannelDao {

    private final ChannelRepository channelRepository;

    public Channel findById(Long id) {
        return channelRepository.findById(id).get();
    }

    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }
}
