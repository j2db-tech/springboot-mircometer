package com.j2db.tech;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessLogic {

    private final Counter counter;

    @Autowired
    public BusinessLogic(MeterRegistry meterRegistry)
    {
        counter=meterRegistry.counter("Invoke");
    }

    public void invoke(){
    counter.increment();
    }
}
