package com.example.demo.messaging;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
public class BasicMessageHandlerIT {

    @Autowired
    private Processor pipe;

    @Test
    public void shouldReceiveMessages() {
        pipe.input().send(MessageBuilder.withPayload("Test").build());
    }
}
