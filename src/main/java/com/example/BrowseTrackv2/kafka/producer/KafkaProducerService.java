package com.example.BrowseTrackv2.kafka.producer;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "networkLogs";  // Updated topic name

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendNetworkLogsFromCSV(String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> logs = csvReader.readAll();
            for (String[] log : logs) {
                StringBuilder logMessage = new StringBuilder();
                for (String field : log) {
                    logMessage.append(field).append(", ");
                }
                if (logMessage.length() > 0) {
                    logMessage.setLength(logMessage.length() - 2);
                }
                // Send each log as a Kafka message
                kafkaTemplate.send(TOPIC, logMessage.toString());
                System.out.println("Sent log message: " + logMessage);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
