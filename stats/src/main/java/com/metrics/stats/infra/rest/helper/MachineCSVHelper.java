package com.metrics.stats.infra.rest.helper;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.exception.RequiredValueException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MachineCSVHelper {
    private static String CONTENT_TYPE = "text/csv";

    public static boolean isCSVFile(MultipartFile file) {
        return CONTENT_TYPE.equals(file.getContentType());
    }

    public static List<Machine> parseFile(InputStream is) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<Machine> machines = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            LocalDateTime currentTime = LocalDateTime.now();

            for (CSVRecord csvRecord : csvRecords) {
                machines.add(new Machine(csvRecord.get("key"), csvRecord.get("name"), currentTime));
            }

            return machines;
        } catch (IOException | RequiredValueException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
