package com.ugarsoft.chyma.iwin.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Chyma on 5/14/2016.
 */
public class AllMonth {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getMonthCode() {
        return monthCode;
    }

    public void setMonthCode(int monthCode) {
        this.monthCode = monthCode;
    }

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String monthName;
    @DatabaseField
    private int monthCode;
}
