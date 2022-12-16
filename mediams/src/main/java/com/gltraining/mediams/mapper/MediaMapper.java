package com.gltraining.mediams.mapper;

import com.gltraining.mediams.dto.MediaChannelDetails;
import com.gltraining.mediams.entity.Media;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MediaMapper {


    public List<MediaChannelDetails> toMediaChannelDetailsList(List<Media> medias) {
        return medias.stream().map(this::toMediaChannelDetails).collect(Collectors.toList());
    }

    public MediaChannelDetails toMediaChannelDetails(Media media) {
        MediaChannelDetails mediaChannelDetails = new MediaChannelDetails();
        mediaChannelDetails.setId(media.getId());
        mediaChannelDetails.setName(media.getName());
        mediaChannelDetails.setTitle(media.getTitle());
        mediaChannelDetails.setWidthResolution(media.getWidthResolution());
        mediaChannelDetails.setHeightResolution(media.getHeightResolution());
        mediaChannelDetails.setUploadedTo(media.getUploadedTo());
        return mediaChannelDetails;
    }
}
