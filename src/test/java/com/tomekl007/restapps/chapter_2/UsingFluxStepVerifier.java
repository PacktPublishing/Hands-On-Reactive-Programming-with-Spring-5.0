package com.tomekl007.restapps.chapter_2;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UsingFluxStepVerifier {
  @Test
  public void should_combine_two_flux_using_concat() {
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
    Flux<Integer> fluxOfIntegers = Flux.concat(
        evenNumbers,
        oddNumbers);

    // then
    StepVerifier.create(fluxOfIntegers)
        .expectNext(0)
        .expectNext(2)
        .expectNext(4)
        .expectNext(1)
        .expectNext(3)
        .expectNext(5)
        .expectComplete() // verify that complete signal was emitted
        .verify();

  }


}
