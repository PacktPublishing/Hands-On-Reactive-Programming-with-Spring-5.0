package com.tomekl007.restapps.chapter_3;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Backpressure {

  @Test
  public void should_employ_backpressure_for_3() {
    AtomicInteger overflow = new AtomicInteger();
    StepVerifier.create(Flux.range(1, 100)
            .hide()
            .log()
            .onBackpressureBuffer(3, overflow::set), 0)
        .expectSubscription()
        .thenRequest(1)
        .expectNext(1) // value 1 is processed
        .thenAwait()
        .thenCancel()
        .verify();

    // buffer at this point will carry values 2,3,4.
    // the value 5 is set (see onBackpressure consumer - overflow::set)
    assertThat(overflow.get()).isEqualTo(5);
  }

}
