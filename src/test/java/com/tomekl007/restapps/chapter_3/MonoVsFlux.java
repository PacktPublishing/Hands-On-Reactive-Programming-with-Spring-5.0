package com.tomekl007.restapps.chapter_3;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MonoVsFlux {

  @Test
  public void should_create_flux_with_one_element() {
    // given
    Flux<Integer> integerFlux = Flux.fromIterable(Arrays.asList(1));
    // can carry 0 to N

    // when
    Flux<Integer> map = integerFlux.map(v -> v * 100);
    Mono<Integer> single = map.single();
    // then
    StepVerifier.create(map)
        .expectNext(100)
        .expectComplete()
        .verify();

    assertThat(single.block()).isEqualTo(100);
  }

  @Test
  public void should_create_mono_with_one_element() {
    // given
    Mono<Integer> mono = Mono.just(1); // can carry at most 1

    // when
    Mono<Integer> map = mono.map(integer -> integer * 100);

    // then
    StepVerifier.create(map)
        .expectNext(100)
        .expectComplete()
        .verify();
  }

  @Test
  public void when_concat_two_mono_should_get_flux() {
    // given
    Mono<Integer> mono1 = Mono.just(1); // can carry at most 1
    Mono<Integer> mono2 = Mono.just(2); // can carry at most 1

    Flux<Integer> integerFlux = mono1.concatWith(mono2); // get flux

    // when
    Flux<Integer> map = integerFlux.map(integer -> integer * 100);

    // then
    StepVerifier.create(map)
        .expectNext(100)
        .expectNext(200)
        .expectComplete()
        .verify();
  }
}
