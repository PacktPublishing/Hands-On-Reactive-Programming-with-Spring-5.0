package com.tomekl007.restapps.chapter_2;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;

public class FlatMapTest {
  @Test
  public void should_execute_flap_map_concurrently() {
    // given

    Flux<Integer> range = Flux.range(0, 1000);

    // when, then
    range
        .buffer(10)
        .log()
        .flatMap(x ->
            Flux.fromIterable(x)
            .map(this::toThreeValues)
            .subscribeOn(Schedulers.parallel())
            .log()
        ).blockLast();

  }

  private List<Integer> toThreeValues(Integer integer) {
    return Arrays.asList(integer + 1, integer + 2, integer + 3);
  }
}
