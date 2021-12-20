package com.metrics.stats.infra.rest.helper;

import com.metrics.stats.domain.Parameter;
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

public class ParameterCSVHelper {
    private static String CONTENT_TYPE = "text/csv";

    public static boolean isCSVFile(MultipartFile file) {
        return CONTENT_TYPE.equals(file.getContentType());
    }

    public static List<Parameter> parseFile(InputStream is) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<Parameter> parameters = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            LocalDateTime now = LocalDateTime.now();
            for (CSVRecord csvRecord : csvRecords) {
                parameters.add(new Parameter(csvRecord.get("machine_key"), csvRecord.get("key"), now, Double.valueOf(csvRecord.get("value"))));
            }
            return parameters;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
