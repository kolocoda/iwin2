package com.ugarsoft.chyma.iwin.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Chyma on 5/14/2016.
 */
public class Disco {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String code;
    @DatabaseField
    private String displayName;
    @DatabaseField
    private Double tariff;
    @DatabaseField
    private Double ePayImageCode;

    public Double getePayImageCode() {
        return ePayImageCode;
    }

    public void setePayImageCode(Double ePayImageCode) {
        this.ePayImageCode = ePayImageCode;
    }
}
