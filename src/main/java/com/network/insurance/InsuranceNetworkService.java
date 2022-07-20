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

    public List<Provider> findProviders(String speciality, String governorateName, String cityName) {
        /*LanguageIdentifier identifier = new LanguageIdentifier(governorateName);
        String language = identifier.getLanguage();
        System.out.println("Language of the given content is : " + language);*/
        List<Provider> providers;
        if (Objects.isNull(governorateName) || Objects.isNull(cityName)) {
            providers = this.findProvidersBySpeciality(speciality);
        } else {
            providers = this.findProvidersBySpecialityAndGovernorateAndCity(speciality, governorateName, cityName);
        }
        return providers;
    }

    public List<Provider> findProvidersBySpeciality(String speciality) {
        List<Provider> providers = providerRepository.findBySpecialityEng(speciality);
        return providers;
    }

    public List<Provider> findProvidersBySpecialityAndGovernorateAndCity(String speciality, String governorateName, String cityName) {
        Governorate governorate = this.getGovernorate(governorateName);
        City city = this.getCity(cityName);
        List<Provider> providers = providerRepository.findBySpecialityEngAndGovernorateIDAndCityID(speciality, governorate.getID(), city.getID());
        return providers;
    }

}
