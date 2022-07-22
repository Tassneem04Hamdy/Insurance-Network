package com.network.insurance;

//import org.apache.tika.language.LanguageIdentifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.sort;

@Service
public class InsuranceNetworkService {
    @Autowired
    GovernorateRepository governorateRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    ProviderRepository providerRepository;

    public Governorate getGovernorate(String governorateName) {
        Governorate governorate = governorateRepository.findByGovernorateEng(governorateName);
        return governorate;
    }

    public City getCity(String cityName) {
        City city = cityRepository.findByCityEng(cityName);
        return city;
    }

    public void update(List<Provider> additions, List<Provider> deletions) {
        providerRepository.saveAll(additions);
        for (int i = 0; i < deletions.size(); i++) {
            Provider provider = providerRepository.findByProviderNameEngAndMapLocation(deletions.get(i).getProviderNameEng(), deletions.get(i).getMapLocation());
            deletions.get(i).setID(provider.getID());
        }
        providerRepository.deleteAll(deletions);
    }

    public List<Provider> compareUpdates(List<Provider> updates) {
        List<Provider> conflicts = new ArrayList<>();
        List<Provider> providers = providerRepository.findAll();
        sort(providers);
        sort(updates);
        // Comparison logic
        return conflicts;
    }

    public List<Provider> findProviders(String speciality, String governorateName, String cityName, String location) {
        /*LanguageIdentifier identifier = new LanguageIdentifier(governorateName);
        String language = identifier.getLanguage();
        System.out.println("Language of the given content is : " + language);*/
        List<Provider> providers;
        if (!(Objects.isNull(governorateName) || Objects.isNull(cityName))) {
            providers = this.findProvidersBySpecialityAndGovernorateAndCity(speciality, governorateName, cityName);
        } else if (!(Objects.isNull(location))) {
            providers = this.findProvidersBySpecialityAndLocation(speciality, location);
        }
        else {
            providers = this.findProvidersBySpeciality(speciality);
        }
        return providers;
    }

    public List<Provider> findProvidersBySpeciality(String speciality) {
        List<Provider> providers = providerRepository.findBySpecialityEng(speciality);
        return providers;
    }

    private List<Provider> findProvidersBySpecialityAndLocation(String speciality, String location) {
        List<Provider> providers = providerRepository.findBySpecialityEng(speciality);
        List<Provider> nearProviders = new ArrayList<>();
        double latitude = Double.parseDouble(location.split(",")[0]);
        double longitude = Double.parseDouble(location.split(",")[1]);
        for (int i = 0; i < providers.size(); i++) {
            double distance = checkDistance(providers.get(i), latitude, longitude);
            if (distance <= 10) nearProviders.add(providers.get(i));
        }
        return nearProviders;
    }

    double checkDistance (Provider provider, double latitude, double longitude) {
        double lat = provider.getLatitude();
        double lon = provider.getLongitude();
        double latDistance = Math.toRadians(latitude - lat);
        double lngDistance = Math.toRadians(longitude - lon);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(lat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double distance = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
        distance = Math.round(AVERAGE_RADIUS_OF_EARTH_KM * distance);
        return distance;
    }

    public List<Provider> findProvidersBySpecialityAndGovernorateAndCity(String speciality, String governorateName, String cityName) {
        Governorate governorate = this.getGovernorate(governorateName);
        City city = this.getCity(cityName);
        List<Provider> providers = providerRepository.findBySpecialityEngAndGovernorateIDAndCityID(speciality, governorate.getID(), city.getID());
        return providers;
    }

}
