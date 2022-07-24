package com.network.insurance.controller;

import com.network.insurance.helper.ExcelReader;
import com.network.insurance.model.Provider;
import com.network.insurance.srvice.InsuranceNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            List<Provider> additions = excelReader.extractData(file.getInputStream(), "New Additions");
            List<Provider> deletions = excelReader.extractData(file.getInputStream(), "Deletion");
            service.update(additions, deletions);
            List<Provider> updates = excelReader.extractData(file.getInputStream(), "Update");

            /*
            List<Provider> conflicts = service.compareUpdates(updates);
            if (!conflicts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is data conflict: " + conflicts);
            }
            */
            return ResponseEntity.status(HttpStatus.OK).body("Updates are successfully done.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse excel file: " + e.getMessage());
        }
    }

    @GetMapping("/providers")
    public ResponseEntity<String> getProviders(@RequestParam(name = "speciality") String speciality,
                                               @RequestParam(name = "governorateName", required = false) String governorateName,
                                               @RequestParam(name = "cityName", required = false) String cityName,
                                               @RequestParam(name = "location", required = false) String location) {
        List<Provider> providers = service.findProviders(speciality, governorateName, cityName, location);
        return ResponseEntity.status(HttpStatus.OK).body("Providers: \n" + providers);
    }

}
