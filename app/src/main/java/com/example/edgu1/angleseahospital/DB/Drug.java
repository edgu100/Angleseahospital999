package com.example.edgu1.angleseahospital.DB;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 11/11/2017.
 */

public class Drug implements Serializable {

    private Integer id;
    private String name;
    private String manufacturer;
    private String milliliters;
    private String productionDate;
    private String shelfLife;
    private String milligrams;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getMilligrams() {
        return milligrams;
    }

    public void setMilligrams(String milligrams) {
        this.milligrams = milligrams;
    }

    public String getMilliliters() {
        return milliliters;
    }

    public void setMilliliters(String milliliters) {
        this.milliliters = milliliters;
    }
}
