package com.gltraining.mediams.service;

import com.gltraining.mediams.constants.MediaConstants;
import com.gltraining.mediams.dto.MediaChannelDetails;
import com.gltraining.mediams.dto.UpdateMediaRequest;
import com.gltraining.mediams.dto.UploadMediaRequest;
import com.gltraining.mediams.entity.Media;
import com.gltraining.mediams.exceptions.InvalidArgumentException;
import com.gltraining.mediams.exceptions.MediaNotFoundException;
import com.gltraining.mediams.mapper.MediaMapper;
import com.gltraining.mediams.repository.IMediaRepository;
import com.gltraining.mediams.util.MediaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MediaService implements IMediaService {
    private IMediaRepository mediaRepository;

    private MediaMapper mediaMapper;

    private MediaUtils mediaUtils;


    @Autowired
    public MediaService(IMediaRepository mediaRepository, MediaMapper mediaMapper, MediaUtils mediaUtils) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
        this.mediaUtils = mediaUtils;
    }


    @Override
    public List<MediaChannelDetails> getMediasByChannelId(Long channelId) {
        return mediaMapper.toMediaChannelDetailsList(mediaRepository.findByUploadedTo(channelId));

    }

    @Override
    public MediaChannelDetails create(UploadMediaRequest uploadMediaRequest) throws InvalidArgumentException {
        validateResolution(uploadMediaRequest.getWidthResolution(), uploadMediaRequest.getHeightResolution());
        Media mediaChannel = new Media();
        mediaChannel.setName(uploadMediaRequest.getName().trim());
        mediaChannel.setTitle(uploadMediaRequest.getTitle().trim());
        mediaChannel.setWidthResolution(uploadMediaRequest.getWidthResolution());
        mediaChannel.setHeightResolution(uploadMediaRequest.getHeightResolution());
        mediaChannel.setUploadedTo(mediaUtils.fetchMediaChannel(uploadMediaRequest.getUploadedTo()).getId());
        return mediaMapper.toMediaChannelDetails(mediaRepository.save(mediaChannel));
    }

    private void validateResolution(Integer widthResolution, Integer heightResolution) throws InvalidArgumentException {
        validateNull(widthResolution, MediaConstants.PARAM_WIDTH_RESOLUTION);
        validateNull(heightResolution, MediaConstants.PARAM_HEIGHT_RESOLUTION);
        if ((widthResolution.equals(480) && heightResolution.equals(620)) ||
                (widthResolution.equals(640) && heightResolution.equals(760)) ||
                (widthResolution.equals(1080) && heightResolution.equals(1400))) {
            return;
        }
        throw new InvalidArgumentException(MediaConstants.INVALID + MediaConstants.PARAM_WIDTH_RESOLUTION + MediaConstants.COLON + widthResolution + MediaConstants.AND + MediaConstants.PARAM_HEIGHT_RESOLUTION + MediaConstants.COLON + heightResolution);


    }


    Media findById(Long mediaId) throws MediaNotFoundException {
        Optional<Media> optionalMedia = mediaRepository.findById(mediaId);
        if (optionalMedia.isEmpty()) {
            throw new MediaNotFoundException(MediaConstants.MEDIA_NOT_FOUND + MediaConstants.PARAM_MEDIA_ID + MediaConstants.COLON + mediaId);
        }
        return optionalMedia.get();
    }


    @Override
    public MediaChannelDetails changeName(UpdateMediaRequest updateChannelRequest) throws MediaNotFoundException {
        Media mediaChannel = findById(updateChannelRequest.getId());
        mediaChannel.setName(updateChannelRequest.getName().trim());
        return mediaMapper.toMediaChannelDetails(mediaRepository.save(mediaChannel));
    }

    @Override
    public Boolean removeMedia(Long mediaId) {
        if (mediaRepository.findById(mediaId).isEmpty()) {
            return false;
        }
        mediaRepository.deleteById(mediaId);
        return true;
    }

    private void validateNull(Object value, String message) throws InvalidArgumentException {
        if (Objects.isNull(value)) {
            throw new InvalidArgumentException(MediaConstants.INVALID + message + MediaConstants.CANT_BE_NULL);
        }
    }


}
