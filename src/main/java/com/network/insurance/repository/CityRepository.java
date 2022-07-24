package com.network.insurance.repository;

import com.network.insurance.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findByCityEng(String cityName);
}
