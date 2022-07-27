package com.network.insurance.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@Table(name = "provider")
@EnableAutoConfiguration
public class Provider implements Comparable<Provider> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String providerNameEng;
    private String addressEng;
    private String specialityEng;
    private String providerNameAr;
    private String addressAr;
    private String specialityAr;
    private String phones;
    private String website;
    private String mapLocation;
    private double latitude;
    private double longitude;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "provider_governorate", joinColumns = @JoinColumn(name = "provider_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "governorate_id", referencedColumnName = "id"))
    private Governorate governorate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "provider_city", joinColumns = @JoinColumn(name = "provider_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"))
    private City city;

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

    public Governorate getGovernorate() {
        return governorate;
    }

    public void setGovernorate(Governorate governorate) {
        this.governorate = governorate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
                ", governorate=" + governorate +
                ", city=" + city +
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
