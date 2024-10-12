package com.example.BrowseTrackv2;

import com.example.BrowseTrackv2.kafka.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrowseTrackv2Application implements CommandLineRunner {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	public static void main(String[] args) {
		SpringApplication.run(BrowseTrackv2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String filePath = "src/main/resources/CIDDS-001-external-week.csv";  // Update with your actual CSV file path
		kafkaProducerService.sendNetworkLogsFromCSV(filePath);
	}
}
