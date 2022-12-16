package com.gltraining.channelms.service;


import com.gltraining.channelms.constants.ChannelConstants;
import com.gltraining.channelms.dto.CreateChannelRequest;
import com.gltraining.channelms.dto.MediaChannelDetails;
import com.gltraining.channelms.dto.UpdateChannelRequest;
import com.gltraining.channelms.exceptions.ChannelNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
public interface IChannelService {

    MediaChannelDetails getChannel(@Min(value = 1, message = ChannelConstants.INVALID + ChannelConstants.PARAM_CHANNEL_ID + ChannelConstants.CANT_BE_LESS_THAN + ChannelConstants.NUM_1)
                                   @PathVariable Long channelId) throws ChannelNotFoundException;

    MediaChannelDetails create(@Valid CreateChannelRequest register);

    MediaChannelDetails changeName(@Valid UpdateChannelRequest butRequest) throws ChannelNotFoundException;

}
