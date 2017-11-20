package com.example.edgu1.angleseahospital.DB;

import java.io.Serializable;

/**
 * Created by Administrator on 11/11/2017.
 */

public class Track implements Serializable {

    private Integer id;
    private Integer patientId;
    private Integer drugsId;
    private String focustime;
    private String realtime;
    private String signature1;
    private String signature2;

    public Integer getId() {
        return id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getDrugsId() {
        return drugsId;
    }

    public String getFocustime() {
        return focustime;
    }

    public String getRealtime() {
        return realtime;
    }

    public String getSignature1() {
        return signature1;
    }

    public String getSignature2() {
        return signature2;
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

    public void setFocustime(String focustime) {
        this.focustime = focustime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public void setSignature1(String signature1) {
        this.signature1 = signature1;
    }

    public void setSignature2(String signature2) {
        this.signature2 = signature2;
    }
}
