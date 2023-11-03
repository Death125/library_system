// package com.library.kafka;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// import com.library.models.entities.Employee;

// @Service
// public class KafkaConsumer {
// private static final Logger LOGGER =
// LoggerFactory.getLogger(KafkaConsumer.class);

// @KafkaListener(topics = "object-saved", groupId = "myGroup")
// public void consume(Employee employee) {
// LOGGER.info(String.format("Message received -> %s", employee.toString()));

// }
// }
