package uk.co.kleindelao.demo.webflux;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello")
public class HelloController {
  @GetMapping(path = "/mono")
  public Mono<String> getMono() {
    return Mono.just("Welcome to the Webflux Demo");
  }

  @GetMapping(path = "/flux", produces = APPLICATION_STREAM_JSON_VALUE)
  public Flux<String> getFlux() {
    return Flux.just("Welcome ", "to ", "the Webflux Demo")
        .delayElements(Duration.ofSeconds(1))
        .log();
  }
}
