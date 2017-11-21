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
    private String dosage;
    private Double frequency;
    private String timeStamp;
    private String signTime;
    private String signImg;
    private String medication;

    public Integer getId() {
        return id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getDrugsId() {
        return drugsId;
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

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setDrugsId(Integer drugsId) {
        this.drugsId = drugsId;
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

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
