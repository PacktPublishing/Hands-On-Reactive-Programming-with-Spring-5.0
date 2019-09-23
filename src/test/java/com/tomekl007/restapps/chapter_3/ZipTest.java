package com.tomekl007.restapps.chapter_3;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ZipTest {

  @Test
  public void should_use_zip_to_join_two_flux() {
    // given
    int min = 1;
    int max = 5;

    Flux<Integer> evenNumbers = Flux
        .range(min, max)
        .filter(x -> x % 2 == 0); // i.e. 2, 4

    Flux<Integer> oddNumbers = Flux
        .range(min, max)
        .filter(x -> x % 2 > 0);  // ie. 1, 3, 5

    // when
    Flux<Integer> fluxOfIntegers = Flux.zip(
        evenNumbers,
        oddNumbers,
        (a, b) -> a + b);

    // then
    StepVerifier.create(fluxOfIntegers)
        .expectNext(3) // 2 + 1
        .expectNext(7) // 4 + 3
        .expectComplete()
        .verify();
  }

  @Test
  public void should_use_zip_with_to_join_two_flux() {
    // given
    int min = 1;
    int max = 5;

    Flux<Integer> evenNumbers = Flux
        .range(min, max)
        .filter(x -> x % 2 == 0); // i.e. 2, 4

    Flux<Integer> oddNumbers = Flux
        .range(min, max)
        .filter(x -> x % 2 > 0);  // ie. 1, 3, 5

    // when
    Flux<Integer> fluxOfIntegers = evenNumbers
        .zipWith(oddNumbers, (a, b) -> a * b);

    // then
    StepVerifier.create(fluxOfIntegers)
        .expectNext(2)  // 2 * 1
        .expectNext(12) // 4 * 3
        .expectComplete()
        .verify();
  }
}

