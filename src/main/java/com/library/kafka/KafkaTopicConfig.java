// package com.library.kafka;

// import org.apache.kafka.clients.admin.NewTopic;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.kafka.config.TopicBuilder;

// @Configuration
// public class KafkaTopicConfig {
// @Bean
// public NewTopic kafkaTopicExample() {
// // Nama topik disini tidak boleh pake spasi atau akan terjadi error
// return TopicBuilder.name("topic-example").build();
// }

// @Bean
// public NewTopic kafkaJsonTopicExample() {
// return TopicBuilder.name("object-saved").build();
// }

// }
