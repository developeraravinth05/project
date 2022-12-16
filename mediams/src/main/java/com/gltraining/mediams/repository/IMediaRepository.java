package com.gltraining.mediams.repository;

import com.gltraining.mediams.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByUploadedTo(Long channelId);
}
