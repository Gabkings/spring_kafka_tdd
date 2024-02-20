package com.example.orderDemoKafka.service;

import com.example.orderDemoKafka.message.OrderCreated;
import com.example.orderDemoKafka.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";
    private final KafkaTemplate<String, Object> kafkaProducer;



    public void process(OrderCreated payLoad) throws ExecutionException, InterruptedException {
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(payLoad.getOrderId())
                .build();

        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();
    }
}
