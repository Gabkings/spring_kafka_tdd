package com.example.orderDemoKafka.utils;

import com.example.orderDemoKafka.message.OrderCreated;

import java.util.UUID;

public class TestEventData {

    public static OrderCreated buildOrderCreatedEvent(UUID orderId, String item){
        return OrderCreated.builder().orderId(orderId).item(item).build();
    }
}
