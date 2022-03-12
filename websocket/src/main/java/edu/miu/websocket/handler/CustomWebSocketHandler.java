package edu.miu.websocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Component
public class CustomWebSocketHandler implements WebSocketHandler {

    private final Sinks.Many<String> sink;

    public CustomWebSocketHandler(Sinks.Many<String> sink) {
        this.sink = sink;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
//        var f= Flux.just("A","B","C","A","B","C")
//                .map(e->session.textMessage(e))
//                .delayElements(Duration.ofSeconds(5));
//       var si= sink.asFlux()
//                .map(s->session.textMessage(s))
//                .delayElements(Duration.ofSeconds(2));

        Flux<WebSocketMessage> receive = session
                .receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(StringBuilder::new)
                .map(StringBuilder::reverse)
                .map(String::new)
                .map(session::textMessage);



        return session.send(receive);
    }
}
