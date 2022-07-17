package com.network.insurance;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelReader {

    @Autowired
    private InsuranceNetworkService service;

    public List<Provider> extractData(InputStream fileIS, String sheetName) {
        try {
            List<Provider> providers = new ArrayList<>();
            ZipSecureFile.setMinInflateRatio(0);
            Workbook workbook = new XSSFWorkbook(fileIS);
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();
            int rowNumber = 0;
            while (rows.hasNext()) {
                rows.next();
                if (rowNumber < 4) {
                    rowNumber++;
                    continue;
                }
                Provider provider = new Provider();
                Row currentCell = sheet.getRow(rowNumber);
                if (currentCell == null) return providers;
                provider.setProviderNameEng(currentCell.getCell(0).getStringCellValue());
                String gov = currentCell.getCell(3).getStringCellValue().trim();
                Governorate governorate = service.getGovernorate(gov);
                provider.setGovernorateID(governorate.getID());
                provider.setCityEng(currentCell.getCell(4).getStringCellValue());
                provider.setAddressEng(currentCell.getCell(5).getStringCellValue());
                DataFormatter formatter = new DataFormatter();
                String val = formatter.formatCellValue(currentCell.getCell(6));
                provider.setPhones(val);
                provider.setSpecialityEng(currentCell.getCell(10).getStringCellValue());
                provider.setSpecialityAr(currentCell.getCell(11).getStringCellValue());
                provider.setWebsite(currentCell.getCell(13).getStringCellValue());
                provider.setLatitude(currentCell.getCell(15).getNumericCellValue());
                provider.setLongitude(currentCell.getCell(16).getNumericCellValue());
                provider.setMapLocation(sheet.getRow(1).getCell(19).getStringCellValue() + provider.getLatitude() + ',' + provider.getLongitude());
                provider.setAddressAr(currentCell.getCell(19).getStringCellValue());
                provider.setCityAr(currentCell.getCell(20).getStringCellValue());
                provider.setProviderNameAr(currentCell.getCell(24).getStringCellValue());
                providers.add(provider);
                rowNumber++;
            }
            workbook.close();
            return providers;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse excel file: " + e.getMessage());
        }
    }
}
