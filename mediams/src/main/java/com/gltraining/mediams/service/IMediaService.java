package com.gltraining.mediams.service;


import com.gltraining.mediams.constants.MediaConstants;
import com.gltraining.mediams.dto.MediaChannelDetails;
import com.gltraining.mediams.dto.UpdateMediaRequest;
import com.gltraining.mediams.dto.UploadMediaRequest;
import com.gltraining.mediams.exceptions.InvalidArgumentException;
import com.gltraining.mediams.exceptions.MediaNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
public interface IMediaService {


    List<MediaChannelDetails> getMediasByChannelId(@Min(value = 1, message = MediaConstants.INVALID + MediaConstants.PARAM_CHANNEL_ID + MediaConstants.CANT_BE_LESS_THAN + MediaConstants.NUM_1)
                                                   @PathVariable Long channelId);

    MediaChannelDetails create(@Valid UploadMediaRequest register) throws InvalidArgumentException;

    MediaChannelDetails changeName(@Valid UpdateMediaRequest butRequest) throws MediaNotFoundException;

    Boolean removeMedia(@Min(value = 1, message = MediaConstants.INVALID + MediaConstants.PARAM_MEDIA_ID + MediaConstants.CANT_BE_LESS_THAN + MediaConstants.NUM_1)
                        @PathVariable Long mediaId);
}
