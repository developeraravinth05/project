package com.gltraining.mediams.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * create request dto
 */
public class UploadMediaRequest {
    @Length(min = 5, max = 20)
    @NotEmpty
    private String name;

    @Length(min = 1, max = 100)
    @NotEmpty
    private String title;

    private Integer widthResolution;

    private Integer heightResolution;

    @Min(1)
    private Long uploadedTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getWidthResolution() {
        return widthResolution;
    }

    public void setWidthResolution(Integer widthResolution) {
        this.widthResolution = widthResolution;
    }

    public Integer getHeightResolution() {
        return heightResolution;
    }

    public void setHeightResolution(Integer heightResolution) {
        this.heightResolution = heightResolution;
    }

    public Long getUploadedTo() {
        return uploadedTo;
    }

    public void setUploadedTo(Long uploadedTo) {
        this.uploadedTo = uploadedTo;
    }
}
