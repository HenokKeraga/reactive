package edu.miu.websocket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Sinks;

@RestController
public class CustomerController {

    private final Sinks.Many<String> sink;

    public CustomerController(Sinks.Many<String> sink) {
        this.sink = sink;
    }

    @PostMapping("/message")
    public void postMessage(){
        sink.emitNext("message",Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
