package com.gltraining.channelms.service;

import com.gltraining.channelms.constants.ChannelConstants;
import com.gltraining.channelms.dto.CreateChannelRequest;
import com.gltraining.channelms.dto.MediaChannelDetails;
import com.gltraining.channelms.dto.UpdateChannelRequest;
import com.gltraining.channelms.entity.MediaChannel;
import com.gltraining.channelms.exceptions.ChannelNotFoundException;
import com.gltraining.channelms.mapper.MediaChannelMapper;
import com.gltraining.channelms.repository.IChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService implements IChannelService {
    private IChannelRepository channelRepository;

    private MediaChannelMapper mediaChannelMapper;

    @Autowired
    public ChannelService(IChannelRepository channelRepository, MediaChannelMapper mediaChannelMapper) {
        this.channelRepository = channelRepository;
        this.mediaChannelMapper = mediaChannelMapper;
    }


    @Override
    public MediaChannelDetails getChannel(Long channelId) throws ChannelNotFoundException {
        Optional<MediaChannel> optionalMedia = channelRepository.findById(channelId);
        if (optionalMedia.isEmpty()) {
            throw new ChannelNotFoundException(ChannelConstants.CHANNEL_NOT_FOUND + ChannelConstants.PARAM_CHANNEL_ID + ChannelConstants.COLON + channelId);
        }
        return mediaChannelMapper.toMediaChannelDetails(optionalMedia.get());
    }

    @Override
    public MediaChannelDetails create(CreateChannelRequest createChannelRequest) {
        MediaChannel mediaChannel = new MediaChannel();
        mediaChannel.setName(createChannelRequest.getName().trim());
        mediaChannel.setCreatedBy(createChannelRequest.getCreatedBy().strip());
        return mediaChannelMapper.toMediaChannelDetails(channelRepository.save(mediaChannel));
    }


    MediaChannel findById(long id) throws ChannelNotFoundException {
        Optional<MediaChannel> optionalMediaChannel = channelRepository.findById(id);
        if (optionalMediaChannel.isEmpty()) {
            throw new ChannelNotFoundException("customer not found");
        }
        return optionalMediaChannel.get();
    }


    @Override
    public MediaChannelDetails changeName(UpdateChannelRequest updateChannelRequest) throws ChannelNotFoundException {
        MediaChannel mediaChannel = findById(updateChannelRequest.getId());
        mediaChannel.setName(updateChannelRequest.getName().trim());

        return mediaChannelMapper.toMediaChannelDetails(channelRepository.save(mediaChannel));
    }
}
