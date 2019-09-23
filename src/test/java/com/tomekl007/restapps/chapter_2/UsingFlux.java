package com.tomekl007.restapps.chapter_2;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.Collector;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UsingFlux {
  @Test
  public void should_combine_two_flux_using_concat() {
    // given
    int min = 0;
    int max = 100;

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
    List<Integer> result = fluxOfIntegers.collectList().block();// blocking call
    assertThat(result.size()).isEqualTo(100);
    // what if we want to test only N first elements?
    // fluxOfIntegers can be an infinite stream - calling block() may stale our program
    assertThat(result.get(0)).isEqualTo(0);
    assertThat(result.get(1)).isEqualTo(2);
    assertThat(result.get(2)).isEqualTo(4);

  }


}
