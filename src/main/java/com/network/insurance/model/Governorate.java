package com.network.insurance.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "governorate")
@EnableAutoConfiguration
public class Governorate {

    @Id
    private int ID;
    private String governorateEng;
    private String governorateAr;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGovernorateEng() {
        return governorateEng;
    }

    public void setGovernorateEng(String governorateEng) {
        this.governorateEng = governorateEng;
    }

    public String getGovernorateAr() {
        return governorateAr;
    }

    public void setGovernorateAr(String governorateAr) {
        this.governorateAr = governorateAr;
    }
}
