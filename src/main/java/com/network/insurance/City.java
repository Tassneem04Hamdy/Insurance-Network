package com.network.insurance;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String cityEng;
    private String cityAr;
    private int governorateID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCityEng() {
        return cityEng;
    }

    public void setCityEng(String cityEng) {
        this.cityEng = cityEng;
    }

    public String getCityAr() {
        return cityAr;
    }

    public void setCityAr(String cityAr) {
        this.cityAr = cityAr;
    }

    public int getGovernorateID() {
        return governorateID;
    }

    public void setGovernorateID(int governorateID) {
        this.governorateID = governorateID;
    }
}
