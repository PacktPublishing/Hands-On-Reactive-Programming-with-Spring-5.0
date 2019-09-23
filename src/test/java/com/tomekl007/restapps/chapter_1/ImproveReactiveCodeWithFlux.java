package com.tomekl007.restapps.chapter_1;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ImproveReactiveCodeWithFlux {

  @Test
  public void should_improve_readability_with_flux() {
    // when
    List<String> res = Flux.range(0, 100)
        .map(v -> action())
        .doOnError(fallbackAction())
        .map(this::executeAsyncCall)
        .collectList()
        .block();

    // then
    System.out.println(res.size());


  }

  private String executeAsyncCall(Supplier<String> v) {
    return "another async call for " + v;
  }


  private Supplier<String> action() {
    return () -> "some action";
  }


  private Consumer<? super Throwable> fallbackAction() {
    return (Consumer<Throwable>) throwable -> System.out.println("execute some fallback for " + throwable);
  }
}
