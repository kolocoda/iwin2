package com.ugarsoft.chyma.iwin.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Chyma on 5/12/2016.
 */
public class EPay {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String area;
    @DatabaseField
    private Double code;
    @DatabaseField
    private int imageCode;
    @DatabaseField
    private boolean ePayIsavailable;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getCode() {
        return code;
    }

    public void setCode(Double code) {
        this.code = code;
    }

    public int getImageCode() {
        return imageCode;
    }

    public void setImageCode(int imageCode) {
        this.imageCode = imageCode;
    }

    public boolean isePayIsavailable() {
        return ePayIsavailable;
    }

    public void setePayIsavailable(boolean ePayIsavailable) {
        this.ePayIsavailable = ePayIsavailable;
    }
}
