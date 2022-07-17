package com.network.insurance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GovernorateRepository extends JpaRepository<Governorate, Integer> {
    Governorate findByGovernorateEng(String governorateName);
}
