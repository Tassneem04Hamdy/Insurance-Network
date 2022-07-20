package com.network.insurance;

import javax.persistence.*;

@Entity
@Table(name = "provider")
public class Provider implements Comparable<Provider> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String providerNameEng;
    //private String cityEng;
    private String addressEng;
    private String specialityEng;
    private String providerNameAr;
    //private String cityAr;
    private String addressAr;
    private String specialityAr;
    private int governorateID;
    private int cityID;
    private String phones;
    private String website;
    private String mapLocation;
    private double latitude;
    private double longitude;

    public Provider() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProviderNameEng() {
        return providerNameEng;
    }

    public void setProviderNameEng(String providerNameEng) {
        this.providerNameEng = providerNameEng;
    }

    public String getAddressEng() {
        return addressEng;
    }

    public void setAddressEng(String addressEng) {
        this.addressEng = addressEng;
    }

    public String getSpecialityEng() {
        return specialityEng;
    }

    public void setSpecialityEng(String specialityEng) {
        this.specialityEng = specialityEng;
    }

    public String getProviderNameAr() {
        return providerNameAr;
    }

    public void setProviderNameAr(String providerNameAr) {
        this.providerNameAr = providerNameAr;
    }

    public String getAddressAr() {
        return addressAr;
    }

    public void setAddressAr(String addressAr) {
        this.addressAr = addressAr;
    }

    public String getSpecialityAr() {
        return specialityAr;
    }

    public void setSpecialityAr(String specialityAr) {
        this.specialityAr = specialityAr;
    }

    public int getGovernorateID() {
        return governorateID;
    }

    public void setGovernorateID(int governorateID) {
        this.governorateID = governorateID;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "ID=" + ID +
                ", providerNameEng='" + providerNameEng + '\'' +
                ", addressEng='" + addressEng + '\'' +
                ", specialityEng='" + specialityEng + '\'' +
                ", providerNameAr='" + providerNameAr + '\'' +
                ", addressAr='" + addressAr + '\'' +
                ", specialityAr='" + specialityAr + '\'' +
                ", governorateID=" + governorateID +
                ", cityID=" + cityID +
                ", phones='" + phones + '\'' +
                ", website='" + website + '\'' +
                ", mapLocation='" + mapLocation + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int compareTo(Provider o) {
        return providerNameEng.compareTo(o.getProviderNameEng());
    }
}
