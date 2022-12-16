package com.gltraining.mediams.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "medias")
@Entity
public class Media {

    @GeneratedValue
    @Id
    private Long id;
    private String name;

    private String title;

    private Integer widthResolution;

    private Integer heightResolution;

    private Long uploadedTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Objects.equals(id, media.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
