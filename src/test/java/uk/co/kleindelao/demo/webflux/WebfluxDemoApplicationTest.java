package uk.co.kleindelao.demo.webflux;

import static org.springframework.http.MediaType.TEXT_PLAIN;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@WebFluxTest
class WebfluxDemoApplicationTest {
  private static final String TEST_MESSAGE = "Welcome to the Webflux Demo";

  @Autowired private WebTestClient webTestClient;

  @Test
  void testMonoEndpoint() {
    Flux<String> msg$ =
        webTestClient
            .get()
            .uri("/hello/mono")
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(String.class)
            .getResponseBody()
            .log();
    msg$.subscribe(System.out::println);

    StepVerifier.create(msg$).expectNext(TEST_MESSAGE).verifyComplete();
  }

  @Test
  void testFunctionalMonoEndpoint() {
    Flux<String> msg$ =
        webTestClient
            .get()
            .uri("/functional/mono")
            .accept(TEXT_PLAIN)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(String.class)
            .getResponseBody()
            .log();

    StepVerifier.create(msg$).expectNext(TEST_MESSAGE).verifyComplete();
  }

  @Test
  public void testFluxEndpoint() {
    Flux<String> msg$ =
        webTestClient
            .get()
            .uri("/hello/flux")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(String.class)
            .getResponseBody()
            .log();
    StepVerifier.create(msg$).expectNext(TEST_MESSAGE).verifyComplete();
  }

  @Test
  public void testFunctionalFluxEndpoint() {
    Flux<String> msg$ =
        webTestClient
            .get()
            .uri("/functional/flux")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(String.class)
            .getResponseBody()
            .log();
    StepVerifier.create(msg$).expectNext(TEST_MESSAGE).verifyComplete();
  }
}
