package com.romanbai.kafka_mikro_notification_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanbai.kafka_mikro_notification_service.dto.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

  @Autowired
  private ObjectMapper objectMapper;  // Для десериализации JSON

  @KafkaListener(topics = "order-topic", groupId = "notification_group")
  public void consume(String orderEventJson) throws Exception {
    OrderEvent orderEvent = objectMapper.readValue(orderEventJson, OrderEvent.class);
    System.out.println("Notification sent for order: " + orderEvent.getDescription());
  }
}
