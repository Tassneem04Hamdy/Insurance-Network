package com.network.insurance.controller;

import com.network.insurance.helper.ExcelReader;
import com.network.insurance.model.City;
import com.network.insurance.model.Governorate;
import com.network.insurance.model.Provider;
import com.network.insurance.repository.CityRepository;
import com.network.insurance.repository.GovernorateRepository;
import com.network.insurance.srvice.InsuranceNetworkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
public class InsuranceNetworkController {
    @Autowired
    InsuranceNetworkService service;
    @Autowired
    ExcelReader excelReader;
    @Autowired
    GovernorateRepository governorateRepository;
    @Autowired
    CityRepository cityRepository;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('" + "ADMIN')")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            List<Provider> additions = excelReader.extractData(file.getInputStream(), "Update");
            List<Provider> deletions = excelReader.extractData(file.getInputStream(), "Deletion");
            service.update(additions, deletions);
            List<Provider> updates = excelReader.extractData(file.getInputStream(), "Update");
            List<Provider> conflicts = service.compareUpdates(updates);
            if (!conflicts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is data conflict: " + conflicts);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Updates are successfully done.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse excel file: " + e.getMessage());
        }
    }

    @GetMapping("/providers")
    public ResponseEntity<?> getProviders(@RequestParam(name = "speciality") String speciality,
                                          @RequestParam(name = "governorateName", required = false) String governorateName,
                                          @RequestParam(name = "cityName", required = false) String cityName,
                                          @RequestParam(name = "location", required = false) String location) {
        if ((Objects.isNull(governorateName) && !Objects.isNull(cityName)) ||
                (!Objects.isNull(governorateName) && Objects.isNull(cityName))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both GovernorateName and CityName must be provided.");
        } else if (Objects.isNull(governorateName) && Objects.isNull(location)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide either GovernorateName and CityName or Location.");
        }
        List<Provider> providers = service.findProviders(speciality, governorateName, cityName, location);
        return ResponseEntity.status(HttpStatus.OK).body(providers);
    }

    @GetMapping("/governorates")
    public ResponseEntity<List<Governorate>> getGovernorates() {
        List<Governorate> governorates = governorateRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(governorates);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getGovernorates(@RequestParam(name = "governorateName") String governorateName) {
        Governorate governorate = service.getGovernorate(governorateName);
        List<City> cities = cityRepository.findByGovernorateID(governorate.getID());
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }
}
