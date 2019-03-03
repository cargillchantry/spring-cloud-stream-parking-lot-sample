package com.example.demo.messaging;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(ProcessorWithParkingLot.class)
public class BasicMessageHandler {

    private final MessageChannel park;

    @Autowired
    BasicMessageHandler(@Qualifier("park") final MessageChannel park) {
        this.park = park;
    }

    @StreamListener(ProcessorWithParkingLot.INPUT)
    public void handleMessages(final Message<String> message) {
        System.out.println(message);
        if(message.getPayload().equals("boom")) {
            throw new AmqpException("Forced failure.");
        }
        if(message.getPayload().equals("fatal")) {
            // really bad (fatal even)
            park.send(new GenericMessage<>(message.getPayload()));
        }
    }
}