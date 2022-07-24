package com.network.insurance.repository;

import com.network.insurance.model.Governorate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GovernorateRepository extends JpaRepository<Governorate, Integer> {
    Governorate findByGovernorateEng(String governorateName);
}
