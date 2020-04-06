package com.ugarsoft.chyma.iwin.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Chyma on 6/8/2016.
 */
public class PowerChart {

    @DatabaseField(generatedId = true)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChartId() {
        return chartId;
    }

    public void setChartId(Long chartId) {
        this.chartId = chartId;
    }

    public float getGenco() {
        return genco;
    }

    public void setGenco(float genco) {
        this.genco = genco;
    }

    public float getNipp() {
        return nipp;
    }

    public void setNipp(float nipp) {
        this.nipp = nipp;
    }

    public float getIpp() {
        return ipp;
    }

    public void setIpp(float ipp) {
        this.ipp = ipp;
    }

    public float getHydro() {
        return hydro;
    }

    public void setHydro(float hydro) {
        this.hydro = hydro;
    }

    public float getThermal() {
        return thermal;
    }

    public void setThermal(float thermal) {
        this.thermal = thermal;
    }

    @DatabaseField
    private Long chartId;
    @DatabaseField
    private float genco;
    @DatabaseField
    private float nipp;
    @DatabaseField
    private float ipp;
    @DatabaseField
    private float hydro;
    @DatabaseField
    private float thermal;
    @DatabaseField
    private Long date;

    private String[] ownerShipNames;
    private double[] ownerShipDistribution;

    private String[] powerTypeNames;

    public double[] getPowerTypeDistribution() {
        return powerTypeDistribution;
    }

    public void setPowerTypeDistribution(double[] powerTypeDistribution) {
        this.powerTypeDistribution = powerTypeDistribution;
    }

    public String[] getPowerTypeNames() {
        return powerTypeNames;
    }

    public void setPowerTypeNames(String[] powerTypeNames) {
        this.powerTypeNames = powerTypeNames;
    }

    private double[] powerTypeDistribution;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String[] getOwnerShipNames() {
        return ownerShipNames;
    }

    public void setOwnerShipNames(String[] ownerShipNames) {
        this.ownerShipNames = ownerShipNames;
    }

    public double[] getOwnerShipDistribution() {
        return ownerShipDistribution;
    }

    public void setOwnerShipDistribution(double[] ownerShipDistribution) {
        this.ownerShipDistribution = ownerShipDistribution;
    }
}
