package com.example.orderDemoKafka.handler;

import com.example.orderDemoKafka.message.OrderCreated;
import com.example.orderDemoKafka.service.DispatchService;
import com.example.orderDemoKafka.utils.TestEventData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler handler;
    private DispatchService dispatchService;

    @BeforeEach
    void setUp(){
        dispatchService = mock(DispatchService.class);
        handler = new OrderCreatedHandler(dispatchService);
    }

    @Test
    void listener() throws Exception {
        OrderCreated orderCreated = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString() );
        handler.listener(orderCreated);
        verify(dispatchService, times(1)).process(orderCreated);
    }

    @Test
    public void listen_ServiceThrowsException() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("Service failure")).when(dispatchService).process(testEvent);
        handler.listener(testEvent);

        verify(dispatchService, times(1)).process(testEvent);
    }
}
