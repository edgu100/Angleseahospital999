package com.example.edgu1.angleseahospital.DB;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 11/11/2017.
 */

public class Patient implements Serializable {

    private Integer id;
    private String name;
    private String roomNo;
    private String NHINo;
    private String birthDay;
    private Double weight;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getNHINo() {
        return NHINo;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public Double getWeight() {
        return weight;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setNHINo(String NHINo) {
        this.NHINo = NHINo;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
