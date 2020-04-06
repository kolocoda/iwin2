package com.ugarsoft.chyma.iwin.models;

/**
 * Created by Chyma on 5/10/2016.
 */
import com.j256.ormlite.field.DatabaseField;

public class PowerStats {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private Long reportId;
    @DatabaseField
    private float availableCapMW;
    @DatabaseField
    private float peakGenMW;
    @DatabaseField
    private float averageGenMW;
    @DatabaseField
    private float gasConstraintMW;
    @DatabaseField
    private float waterConstraintMW;
    @DatabaseField
    private float loadRejectionMW;
    @DatabaseField
    private float transmissionConstraintMW;
    @DatabaseField
    private float totalMW;
    @DatabaseField
    private long date;

    //In app us
    private double[] averageGenerationData;
    private double[] peakGenerationData;
    private double[] totalLossData;

    public double[] getPeakGenerationData() {
        return peakGenerationData;
    }

    public void setPeakGenerationData(double[] peakGenerationData) {
        this.peakGenerationData = peakGenerationData;
    }

    public double[] getTotalLossData() {
        return totalLossData;
    }

    public void setTotalLossData(double[] totalLossData) {
        this.totalLossData = totalLossData;
    }

    public double[] getAvailableGenerationData() {
        return availableGenerationData;
    }

    public void setAvailableGenerationData(double[] availableGenerationData) {
        this.availableGenerationData = availableGenerationData;
    }

    private double[] availableGenerationData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAvailableCapMW() {
        return availableCapMW;
    }

    public void setAvailableCapMW(float availableCapMW) {
        this.availableCapMW = availableCapMW;
    }

    public float getPeakGenMW() {
        return peakGenMW;
    }

    public void setPeakGenMW(float peakGenMW) {
        this.peakGenMW = peakGenMW;
    }

    public float getAverageGenMW() {
        return averageGenMW;
    }

    public void setAverageGenMW(float averageGenMW) {
        this.averageGenMW = averageGenMW;
    }

    public float getGasConstraintMW() {
        return gasConstraintMW;
    }

    public void setGasConstraintMW(float gasConstraintMW) {
        this.gasConstraintMW = gasConstraintMW;
    }

    public float getWaterConstraintMW() {
        return waterConstraintMW;
    }

    public void setWaterConstraintMW(float waterConstraintMW) {
        this.waterConstraintMW = waterConstraintMW;
    }

    public float getLoadRejectionMW() {
        return loadRejectionMW;
    }

    public void setLoadRejectionMW(float loadRejectionMW) {
        this.loadRejectionMW = loadRejectionMW;
    }

    public float getTransmissionConstraintMW() {
        return transmissionConstraintMW;
    }

    public void setTransmissionConstraintMW(float transmissionConstraintMW) {
        this.transmissionConstraintMW = transmissionConstraintMW;
    }

    public float getTotalMW() {
        return totalMW;
    }

    public void setTotalMW(float totalMW) {
        this.totalMW = totalMW;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double[] getAverageGenerationData() {
        return averageGenerationData;
    }

    public void setAverageGenerationData(double[] averageGenerationData) {
        this.averageGenerationData = averageGenerationData;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
}
