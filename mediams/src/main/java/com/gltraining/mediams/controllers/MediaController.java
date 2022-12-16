package com.gltraining.mediams.controllers;

import com.gltraining.mediams.constants.MediaConstants;
import com.gltraining.mediams.dto.MediaChannelDetails;
import com.gltraining.mediams.dto.UpdateMediaRequest;
import com.gltraining.mediams.dto.UploadMediaRequest;
import com.gltraining.mediams.exceptions.InvalidArgumentException;
import com.gltraining.mediams.exceptions.MediaNotFoundException;
import com.gltraining.mediams.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;


@RequestMapping("/medias")
@RestController
public class MediaController {

    private final IMediaService mediaService;

    @Autowired
    public MediaController(IMediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/byChannelId/{channelId}")
    public List<MediaChannelDetails> getMediaByChannelId(@Min(value = 1, message = MediaConstants.INVALID + MediaConstants.PARAM_CHANNEL_ID + MediaConstants.CANT_BE_LESS_THAN + MediaConstants.NUM_1)
                                                         @PathVariable Long channelId) {
        return mediaService.getMediasByChannelId(channelId);
    }

    @PostMapping(value = "/upload")
    public MediaChannelDetails upload(@RequestBody UploadMediaRequest request) throws InvalidArgumentException {
        return mediaService.create(request);
    }


    @PutMapping("/changeName")
    public MediaChannelDetails changeName(@RequestBody UpdateMediaRequest request) throws MediaNotFoundException {
        return mediaService.changeName(request);
    }

    @DeleteMapping("/removeById/{mediaId}")
    public Boolean remove(@Min(value = 1, message = MediaConstants.INVALID + MediaConstants.PARAM_MEDIA_ID + MediaConstants.CANT_BE_LESS_THAN + MediaConstants.NUM_1)
                          @PathVariable Long mediaId) {
        return mediaService.removeMedia(mediaId);
    }

}
