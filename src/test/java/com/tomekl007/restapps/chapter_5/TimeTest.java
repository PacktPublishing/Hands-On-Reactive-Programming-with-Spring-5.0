package com.tomekl007.restapps.chapter_5;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class TimeTest {
  @Test
  public void should_test_time() {
    VirtualTimeScheduler.getOrSet();

    Flux<Long> flux = Flux
        .interval(Duration.ofSeconds(10), Duration.ofSeconds(5))
        .take(3);

    StepVerifier.withVirtualTime(() -> flux)
        .expectSubscription()
        .thenAwait(Duration.ofSeconds(10))
        .expectNext(0L)
        .thenAwait(Duration.ofSeconds(5))
        .expectNext(1L)
        .thenAwait(Duration.ofSeconds(5))
        .expectNext(2L)
        .verifyComplete();
  }
}
