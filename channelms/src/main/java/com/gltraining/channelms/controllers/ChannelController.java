package com.gltraining.channelms.controllers;

import com.gltraining.channelms.constants.ChannelConstants;
import com.gltraining.channelms.dto.CreateChannelRequest;
import com.gltraining.channelms.dto.MediaChannelDetails;
import com.gltraining.channelms.dto.UpdateChannelRequest;
import com.gltraining.channelms.exceptions.ChannelNotFoundException;
import com.gltraining.channelms.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;


@RequestMapping("/channels")
@RestController
public class ChannelController {

    private IChannelService service;

    @Autowired
    public ChannelController(IChannelService service) {
        this.service = service;
    }

    @GetMapping("/byId/{channelId}")
    public MediaChannelDetails getChannel(@Min(value = 1, message = ChannelConstants.INVALID + ChannelConstants.PARAM_CHANNEL_ID + ChannelConstants.CANT_BE_LESS_THAN + ChannelConstants.NUM_1)
                                          @PathVariable Long channelId) throws ChannelNotFoundException {
        return service.getChannel(channelId);
    }

    @PostMapping(value = "/create")
    public MediaChannelDetails create(@RequestBody CreateChannelRequest request) {
        return service.create(request);
    }


    @PutMapping("/changeName")
    public MediaChannelDetails changeName(@RequestBody UpdateChannelRequest request) throws ChannelNotFoundException {
        return service.changeName(request);
    }

}
