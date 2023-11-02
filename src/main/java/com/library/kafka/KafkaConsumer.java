package com.library.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    // Subscribe kafka topic from kafka consumer
    // groupId ini merupakan groupId yang telah dibuat sebelumnya pada
    // application.properties
    @KafkaListener(topics = "KafkaTopicExample", groupId = "myGroup")
    public void consume(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}
