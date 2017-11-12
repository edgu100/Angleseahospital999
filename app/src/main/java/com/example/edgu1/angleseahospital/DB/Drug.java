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
    private String specification;
    private Date productionDate;
    private Date shelfLife;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSpecification() {
        return specification;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public Date getShelfLife() {
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

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }
}
