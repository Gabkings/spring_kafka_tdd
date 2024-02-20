package com.example.orderDemoKafka.handler;


import com.example.orderDemoKafka.message.OrderCreated;
import com.example.orderDemoKafka.service.DispatchService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreatedHandler {

    private DispatchService dispatchService;

    @KafkaListener(
            id = "orderConsumerClient",
            topics = "order.created",
            groupId = "dispatch.order.created.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listener(OrderCreated payLoad){
        log.info("Message received payload: "+ payLoad);
        try {
            dispatchService.process(payLoad);
        } catch (Exception e) {
            log.error("Processing failure", e);
        }

    }
}
