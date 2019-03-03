package com.example.demo.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;

public interface ProcessorWithParkingLot extends Processor {
    String PARK = "park";
    @Output(PARK)
    MessageChannel park();
}
