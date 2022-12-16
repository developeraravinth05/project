package com.gltraining.channelms.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * create request dto
 */
public class CreateChannelRequest {
    @Length(min = 3, max = 20)
    @NotEmpty
    private String name;
    private String createdBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
