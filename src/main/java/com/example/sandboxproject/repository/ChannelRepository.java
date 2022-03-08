package com.example.sandboxproject.repository;

import com.example.sandboxproject.Entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel,Long> {
}
