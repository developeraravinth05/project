package com.gltraining.channelms.repository;

import com.gltraining.channelms.entity.MediaChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChannelRepository extends JpaRepository<MediaChannel, Long> {
}
