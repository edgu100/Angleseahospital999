package com.example.edgu1.angleseahospital.DB;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 11/11/2017.
 */

public class PatientDrugs implements Serializable {

    private Integer id;
    private Integer patientId;
    private Integer drugsId;
    private String dosageStart;
    private String dosageEnd;
    private Double frequency;
    private String timeStamp;
    private String signTime;
    private String signImg;

    public Integer getId() {
        return id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getDrugsId() {
        return drugsId;
    }

    public String getDosageStart() {
        return dosageStart;
    }

    public String getDosageEnd() {
        return dosageEnd;
    }

    public Double getFrequency() {
        return frequency;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSignTime() {
        return signTime;
    }

    public String getSignImg() {
        return signImg;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setDrugsId(Integer drugsId) {
        this.drugsId = drugsId;
    }

    public void setDosageStart(String dosageStart) {
        this.dosageStart = dosageStart;
    }

    public void setDosageEnd(String dosageEnd) {
        this.dosageEnd = dosageEnd;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }
}
