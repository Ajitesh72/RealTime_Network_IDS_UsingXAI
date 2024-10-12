package com.example.BrowseTrackv2.kafka.consumer;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.example.BrowseTrackv2.model.NetworkLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class KafkaConsumerService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @KafkaListener(topics = "networkLogs", groupId = "network_logs_group")
    public void consumeNetworkLog(String logMessage) {
        // Create a NetworkLog object
        System.out.println("atleast yaha toh aaya");
        NetworkLog networkLog = new NetworkLog(UUID.randomUUID().toString(), logMessage);

        // Send the data to Elasticsearch
        try {
            IndexRequest<NetworkLog> request = IndexRequest.of(i -> i
                    .index("network_logs")  // The index where the logs will be stored
                    .id(networkLog.getId())
                    .document(networkLog)
            );
            IndexResponse response = elasticsearchClient.index(request);
            System.out.println("Document indexed with ID: " + response.id());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}