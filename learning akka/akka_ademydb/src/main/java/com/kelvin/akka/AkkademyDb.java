package com.kelvin.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.kelvin.akka.messages.SetRequest;

import java.util.HashMap;
import java.util.Map;

public class AkkademyDb extends AbstractActor {

    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    protected final Map<String, Object> map = new HashMap<>();
    public AkkademyDb() {

        StringBuilder builder = new StringBuilder();


        receive(ReceiveBuilder.match(SetRequest.class, message -> {
           log.info("Received Set request: {}", message);
           map.put(message.getKey(), message.getValue());
        }).matchAny(o -> {
            log.info("received unknown message: {}", o);
        }).build());
    }
}
