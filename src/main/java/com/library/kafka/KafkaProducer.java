// package com.library.kafka;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.support.KafkaHeaders;
// import org.springframework.messaging.Message;
// import org.springframework.messaging.support.MessageBuilder;
// import org.springframework.stereotype.Service;

// import com.library.models.entities.Employee;

// @Service
// public class KafkaProducer {
// private static final Logger LOGGER =
// LoggerFactory.getLogger(KafkaProducer.class);

// private KafkaTemplate<String, Employee> kafkaTemplate;

// public KafkaProducer(KafkaTemplate<String, Employee> kafkaTemplate) {
// this.kafkaTemplate = kafkaTemplate;
// }

// public void sendMessage(Employee employeeData) {
// LOGGER.info(String.format("Message sent %s", employeeData.toString()));

// Message<Employee> message = MessageBuilder.withPayload(employeeData)
// .setHeader(KafkaHeaders.TOPIC, "object-saved").build();

// kafkaTemplate.send(message);
// }
// }
