package com.network.insurance.repository;

import com.network.insurance.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Provider findByProviderNameEngAndMapLocation(String providerNameEng, String mapLocation);
    List<Provider> findBySpecialityEng(String speciality);
    List<Provider> findBySpecialityEngAndGovernorateIDAndCityID(String speciality, int governorateID, int cityID);
}