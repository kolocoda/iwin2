package com.ugarsoft.chyma.iwin.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Chyma on 5/11/2016.
 */
public class SubAppliance {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private Long code;
    @DatabaseField
    private String title;
    @DatabaseField
    private String shortDesc;
    @DatabaseField
    private String fullDesc;
    @DatabaseField
    private int imageCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public int getImageCode() {
        return imageCode;
    }

    public void setImageCode(int imageCode) {
        this.imageCode = imageCode;
    }
}
