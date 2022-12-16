package com.gltraining.mediams.util;

import com.gltraining.mediams.dto.MediaChannelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MediaUtils {

    @Value("${channelms.baseurl}")
    private String baseChannelUrl;

    private RestTemplate restTemplate;

    @Autowired
    public MediaUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MediaChannelDTO fetchMediaChannel(Long mediaChannelId) {
        String url = baseChannelUrl + "/channels/byId/" + mediaChannelId;
        ResponseEntity<MediaChannelDTO> responseEntity = restTemplate.getForEntity(url, MediaChannelDTO.class);
        return responseEntity.getBody();
    }
}
