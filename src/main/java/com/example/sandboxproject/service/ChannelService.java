package com.example.sandboxproject.service;

import com.example.sandboxproject.Entity.Channel;
import com.example.sandboxproject.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public Channel findById(Long id) {
        return channelRepository.findById(id).get();
    }

    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }
}
