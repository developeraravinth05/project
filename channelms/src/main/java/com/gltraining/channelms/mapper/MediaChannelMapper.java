package com.gltraining.channelms.mapper;

import com.gltraining.channelms.dto.MediaChannelDetails;
import com.gltraining.channelms.entity.MediaChannel;
import org.springframework.stereotype.Component;

@Component
public class MediaChannelMapper {


    public MediaChannelDetails toMediaChannelDetails(MediaChannel mediaChannel) {
        MediaChannelDetails mediaChannelDetails = new MediaChannelDetails();
        mediaChannelDetails.setId(mediaChannel.getId());
        mediaChannelDetails.setName(mediaChannel.getName());
        mediaChannelDetails.setCreatedBy(mediaChannel.getCreatedBy());
        return mediaChannelDetails;
    }

}
