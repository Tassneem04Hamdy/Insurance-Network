package com.network.insurance.repository;

import com.network.insurance.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Boolean existsByProviderNameEngAndMapLocationAndSpecialityEng(String providerNameEng, String mapLocation, String specialityEng);
    Provider findByProviderNameEngAndMapLocationAndSpecialityEng(String providerNameEng, String mapLocation, String specialityEng);
    List<Provider> findBySpecialityEng(String speciality);
    List<Provider> findBySpecialityEngAndGovernorateIDAndCityID(String speciality, int governorateID, int cityID);
}
