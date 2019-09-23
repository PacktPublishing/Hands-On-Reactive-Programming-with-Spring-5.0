package com.tomekl007.restapps.chapter_2;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class DelayPublisherTest {
  @Test
  public void givenFluxes_whenMergeWithDelayedElementsIsInvoked_thenMergeWithDelayedElements() {
    // given
    int min = 0;
    int max = 6;

    Flux<Integer> evenNumbers = Flux
        .range(min, max)
        .filter(x -> x % 2 == 0); // i.e. 2, 4

    Flux<Integer> oddNumbers = Flux
        .range(min, max)
        .filter(x -> x % 2 > 0);  // ie. 1, 3, 5

    // when
    Flux<Integer> fluxOfIntegers = Flux.merge(
        evenNumbers.delayElements(Duration.ofMillis(500L)),
        oddNumbers.delayElements(Duration.ofMillis(300L)));

    // then
    StepVerifier.create(fluxOfIntegers)
        .expectNext(1)
        .expectNext(0)
        .expectNext(3)
        .expectNext(5)
        .expectNext(2)
        .expectNext(4)
        .expectComplete()
        .verify();
  }
}
