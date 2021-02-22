package uk.co.kleindelao.demo.webflux;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;

import java.time.Duration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
  public Mono<ServerResponse> monoMessage(final ServerRequest request) {
    return ServerResponse.ok()
        .contentType(TEXT_PLAIN)
        .body(Mono.just("Welcome to the functional Webflux Demo"), String.class);
  }

  public Mono<ServerResponse> fluxMessage(final ServerRequest request) {
    return ServerResponse.ok()
        .contentType(APPLICATION_STREAM_JSON)
        .body(
            Flux.just("Welcome ", "to ", "the ", "functional ", "Webflux ", "Demo")
                .delayElements(Duration.ofSeconds(1))
                .log(),
            String.class);
  }
}
